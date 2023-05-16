package com.theduckfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.databinding.ActivityOrderPaymentBinding;
import com.theduckfood.databinding.ItemOrderPaymentBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Coupon;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.List;

public class OrderPaymentAdapter extends RecyclerView.Adapter<OrderPaymentAdapter.OrderPaymentViewHolder>{
    ItemOrderPaymentBinding binding;
    Context context;
    List<CartItem> cartItems;

    SharedPreferenceManager sharedPreferenceManager;
    Long storeId;
    ActivityOrderPaymentBinding activityOrderPaymentBinding;
    int totalPrice;
    Coupon coupon;

    public OrderPaymentAdapter(Context context, List<CartItem> cartItems, Long storeId, ActivityOrderPaymentBinding activityOrderPaymentBinding) {
        this.context = context;
        this.cartItems = cartItems;
        this.storeId = storeId;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
        this.activityOrderPaymentBinding = activityOrderPaymentBinding;
    }

    @NonNull
    @Override
    public OrderPaymentAdapter.OrderPaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderPaymentBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new OrderPaymentAdapter.OrderPaymentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPaymentViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        String urlImage = cartItem.getFood().getImage().startsWith("http") ? cartItem.getFood().getImage() : Constant.IMAGE_BASE_URL + cartItem.getFood().getImage();
        Glide.with(context)
                .load(urlImage)
                .into(binding.imgMonAn);
        holder.binding.txtTenMonAn.setText(cartItem.getFood().getFoodName());
        String price = DateTimeUtil.formatCurrency(String.valueOf(cartItem.getFood().getPrice())) + " đ";
        holder.binding.txtGiaMonAn.setText(price);
        holder.binding.txtAmount.setText(String.valueOf(cartItem.getAmount()));
        holder.binding.btnTangSoLuong.setOnClickListener(v -> increaseAmount(holder, cartItem));
        holder.binding.btnGiamSoLuong.setOnClickListener(v -> decreaseAmount(holder, cartItem));

    }
    private void decreaseAmount(@NonNull OrderPaymentViewHolder holder, CartItem cartItem) {
        int amount =  Integer.parseInt(holder.binding.txtAmount.getText().toString());
        if (amount > 1) {
            amount -= 1;
            holder.binding.txtAmount.setText(String.valueOf(amount));
        }  else if (amount == 1) {
            cartItems.remove(cartItem);
            amount -= 1;
            notifyDataSetChanged();
        }
        cartItem.setAmount(amount);
        sharedPreferenceManager.addCartItem(cartItem, storeId);
        updatePriceDetail();
    }

    private void increaseAmount(@NonNull OrderPaymentViewHolder holder, CartItem cartItem) {
        int amount =  Integer.parseInt(holder.binding.txtAmount.getText().toString());
        amount += 1;
        holder.binding.txtAmount.setText(String.valueOf(amount));
        cartItem.setAmount(amount);
        sharedPreferenceManager.addCartItem(cartItem, storeId);
        updatePriceDetail();
    }
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
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
        activityOrderPaymentBinding.txtTongGiaTienMonAn.setText(priceCart);

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
                activityOrderPaymentBinding.txtCoupon.setText("Coupon không phù hợp!");
            }
        }
        String discountOrder = DateTimeUtil.formatCurrency(String.valueOf(discount));
        activityOrderPaymentBinding.txtTienGiamGia.setText(discountOrder);

        String totalPriceOrder = DateTimeUtil.formatCurrency(String.valueOf(totalPrice + shipFee + serviceFee - discount));
        activityOrderPaymentBinding.txtThanhTien.setText(totalPriceOrder);
    }
    @Override
    public int getItemCount() {
        return cartItems == null ? 0 : cartItems.size();
    }

    public class OrderPaymentViewHolder extends RecyclerView.ViewHolder {
        ItemOrderPaymentBinding binding;

        public OrderPaymentViewHolder(ItemOrderPaymentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
