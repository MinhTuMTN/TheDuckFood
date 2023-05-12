package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.merchant.databinding.ItemChiTietHoaDonBinding;
import com.theduckfood.merchant.model.response.OrderItemResponse;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    Context context;
    List<OrderItemResponse> orderItemResponses;

    public OrderItemAdapter(Context context, List<OrderItemResponse> orderItemResponses) {
        this.context = context;
        this.orderItemResponses = orderItemResponses;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(
                ItemChiTietHoaDonBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItemResponse orderItemResponse = orderItemResponses.get(position);

        String amount = orderItemResponse.getAmount() + "x";
        holder.binding.txtSoLuong.setText(amount);

        holder.binding.txtTenMonAn.setText(orderItemResponse.getFoodName());
        String price = DateTimeUtil.formatCurrency(String.valueOf(orderItemResponse.getPrice()));
        holder.binding.txtGiaTienMonAn.setText(price);
     }

    @Override
    public int getItemCount() {
        return orderItemResponses.size();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        ItemChiTietHoaDonBinding binding;

        public OrderItemViewHolder(ItemChiTietHoaDonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
