package com.theduckfood.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.theduckfood.adapter.OrderPaymentAdapter;
import com.theduckfood.databinding.ActivityOrderPaymentBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Coupon;
import com.theduckfood.model.Store;
import com.theduckfood.model.UserAddress;
import com.theduckfood.model.request.CreateOrderRequest;
import com.theduckfood.model.request.FoodRequest;
import com.theduckfood.model.respone.CreateOrderResponse;
import com.theduckfood.presenter.CreateOrderPresenter;
import com.theduckfood.presenter.contact.ICreateOrderView;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class OrderPaymentActivity extends AppCompatActivity implements ICreateOrderView {
    ActivityOrderPaymentBinding binding;
    OrderPaymentAdapter orderPaymentAdapter;
    ActivityResultLauncher<Intent> couponActivityResultLauncher;
    ActivityResultLauncher<Intent> addressActivityResultLauncher;
    List<CartItem> cartItems;
    int totalPrice;
    Coupon coupon;
    UserAddress userAddress;
    Store store;
    SharedPreferenceManager sharedPreferenceManager;
    CreateOrderPresenter createOrderPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferenceManager = new SharedPreferenceManager(this);
        getDataIntent();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnAddMoreFoods.setOnClickListener(v -> onBackPressed());
        binding.btnChangeAddress.setOnClickListener(v -> switchToUserAddressActivity());
        binding.cardCoupon.setOnClickListener(v -> switchToUseCouponActivity());
        binding.btnTaoDonHang.setOnClickListener(v -> btnTaoDonHangClick());
    }

    private void switchToUseCouponActivity() {
        Intent intent = new Intent(this, UseCouponActivity.class);
        intent.putExtra("storeId", store.getStoreId());
        couponActivityResultLauncher.launch(intent);
    }

    private void switchToUserAddressActivity() {
        Intent intent = new Intent(this, UserAddressActivity.class);
        addressActivityResultLauncher.launch(intent);
    }

    public void updatePriceOrder() {
        int price = 0;
        for (CartItem cartItem : sharedPreferenceManager.getCartItems()) {
            price += cartItem.getFood().getPrice() * cartItem.getAmount();
        }
        totalPrice = price;
    }

    public void updatePriceDetail() {
        updatePriceOrder();
        String priceCart = DateTimeUtil.formatCurrency(String.valueOf(totalPrice));
        binding.txtTongGiaTienMonAn.setText(priceCart);

        int discount = 0;
        if(coupon != null) {
            if (totalPrice >= coupon.getMinPrice()) {
                if (totalPrice * coupon.getDiscount() <= coupon.getMaxDiscount()) {
                    discount = (int) (totalPrice * coupon.getDiscount());
                } else {
                    discount = (int) Math.round(coupon.getMaxDiscount());
                }
            } else {
                coupon = null;
                binding.txtCoupon.setText("Coupon không phù hợp!");
            }
        }
        String discountOrder = DateTimeUtil.formatCurrency(String.valueOf(discount));
        binding.txtTienGiamGia.setText(discountOrder);

        String totalPriceOrder = DateTimeUtil.formatCurrency(
                String.valueOf(
                        totalPrice + Constant.SERVICE_FEE + Constant.SHIP_FEE - discount
                )
        );
        binding.txtThanhTien.setText(totalPriceOrder);
    }
    private void getDataIntent() {
        store = (Store) getIntent().getSerializableExtra("store");

        couponActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        coupon = (Coupon) result.getData().getSerializableExtra("coupon");
                        if (coupon != null) {
                            binding.txtCoupon.setText(coupon.getCouponCode());
                            updatePriceDetail();
                            orderPaymentAdapter.setCoupon(coupon);
                        }
                    }

                });

        addressActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        userAddress = (UserAddress) result.getData().getSerializableExtra("userAddress");
                        if (userAddress != null)
                            binding.txtDiaChiHienTai.setText(userAddress.getStreetAddress());
                    }

                });
    }

    private void loadData() {
        binding.txtTenQuan.setText(store.getStoreName());
        updatePriceDetail();
        getOrderItemList();
    }

    private void getOrderItemList() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        cartItems = sharedPreferenceManager.getCartItems();
        orderPaymentAdapter = new OrderPaymentAdapter(this, cartItems, store.getStoreId(), binding);
        binding.recyclerFoods.setAdapter(orderPaymentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerFoods.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("store", store.getStoreId());
        startActivity(intent);
        super.onBackPressed();
    }


    private void btnTaoDonHangClick() {
        String couponCode = "";
        if (coupon != null )
            couponCode = coupon.getCouponCode();

        Long storeId = store.getStoreId();
        Long addressId;
        if (userAddress == null) {
            Toast.makeText(this, "Vui lòng chọn địa chỉ!", Toast.LENGTH_SHORT).show();
            return;
        }
        addressId = userAddress.getUserAddressId();
        List<FoodRequest> foodRequestList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            FoodRequest foodRequest = new FoodRequest(cartItem.getFood().getFoodId(), cartItem.getAmount());
            foodRequestList.add(foodRequest);
        }
        if (foodRequestList.isEmpty()){
            Toast.makeText(this, "Đơn hàng rỗng! Chọn món đi!", Toast.LENGTH_SHORT).show();
            return;
        }
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(couponCode, storeId, addressId, foodRequestList);
        createOrderPresenter = new CreateOrderPresenter(this, this);
        createOrderPresenter.createOrder(createOrderRequest);
    }
    @Override
    public void createOrder(CreateOrderResponse createOrderResponse) {
        if (createOrderResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        sharedPreferenceManager.clearCartItems();
        Toast.makeText(this, createOrderResponse.getMessage(), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

}