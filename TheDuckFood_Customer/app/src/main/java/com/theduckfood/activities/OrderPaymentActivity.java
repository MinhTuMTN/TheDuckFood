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

import com.theduckfood.adapter.OrderPaymentAdapter;
import com.theduckfood.databinding.ActivityOrderPaymentBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Coupon;
import com.theduckfood.model.Store;
import com.theduckfood.model.UserAddress;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.List;

public class OrderPaymentActivity extends AppCompatActivity {
    ActivityOrderPaymentBinding binding;

    OrderPaymentAdapter orderPaymentAdapter;

    ActivityResultLauncher<Intent> couponActivityResultLauncher;
    ActivityResultLauncher<Intent> addressActivityResultLauncher;
    List<CartItem> cartItems;
    int totalPrice;
    Coupon coupon;
    Store store;
    SharedPreferenceManager sharedPreferenceManager;
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
        binding.btnBack.setOnClickListener(v -> switchToStoreDetailActivity(store.getStoreId()));
        binding.btnAddMoreFoods.setOnClickListener(v -> switchToStoreDetailActivity(store.getStoreId()));
        binding.btnChangeAddress.setOnClickListener(v -> switchToUserAddressActivity());
        binding.cardCoupon.setOnClickListener(v -> switchToUseCouponActivity(store.getStoreId()));
    }

    private void switchToUseCouponActivity(Long storeId) {
        Intent intent = new Intent(this, UseCouponActivity.class);
        intent.putExtra("storeId", storeId);
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

        int shipFee = 15000;
        int serviceFee = 2000;

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

        String totalPriceOrder = DateTimeUtil.formatCurrency(String.valueOf(totalPrice + shipFee + serviceFee - discount));
        binding.txtThanhTien.setText(totalPriceOrder);
    }
    private void getDataIntent() {
        store = (Store) getIntent().getSerializableExtra("store");

        couponActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            coupon = (Coupon) result.getData().getSerializableExtra("coupon");
                            binding.txtCoupon.setText(coupon.getCouponCode());
                            updatePriceDetail();
                        }

                    }
                });

        addressActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            UserAddress userAddress = (UserAddress) result.getData().getSerializableExtra("userAddress");
                            binding.txtDiaChiHienTai.setText(userAddress.getStreetAddress());
                        }

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
        orderPaymentAdapter = new OrderPaymentAdapter(this, cartItems, store.getStoreId());
        binding.recyclerFoods.setAdapter(orderPaymentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerFoods.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    private void switchToStoreDetailActivity(Long storeId) {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("store", storeId);
        startActivity(intent);
    }
}