package com.theduckfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.databinding.ItemOrderPaymentBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;

import java.util.List;

public class OrderPaymentAdapter extends RecyclerView.Adapter<OrderPaymentAdapter.OrderPaymentViewHolder>{
    ItemOrderPaymentBinding binding;
    Context context;
    List<CartItem> cartItems;

    public OrderPaymentAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
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
        holder.binding.btnTangSoLuong.setOnClickListener(v -> increaseAmount(position));
        holder.binding.btnGiamSoLuong.setOnClickListener(v -> decreaseAmount(position));

    }
    private void decreaseAmount(int position) {
        int amount =  Integer.parseInt(binding.txtAmount.getText().toString());
        if (amount > 0) {
            amount -= 1;
            binding.txtAmount.setText(String.valueOf(amount));
        }
    }

    private void increaseAmount(int position) {
        int amount =  Integer.parseInt(binding.txtAmount.getText().toString());
        amount += 1;
        binding.txtAmount.setText(String.valueOf(amount));

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
