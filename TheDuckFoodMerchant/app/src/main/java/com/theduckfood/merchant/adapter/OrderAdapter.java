package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.merchant.databinding.ItemOrderBinding;
import com.theduckfood.merchant.model.response.OrderItemResponse;
import com.theduckfood.merchant.model.response.StoreOrderResponse;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    List<StoreOrderResponse> orderResponses;

    public OrderAdapter(Context context, List<StoreOrderResponse> orderResponses) {
        this.context = context;
        this.orderResponses = orderResponses;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(
                ItemOrderBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        StoreOrderResponse orderResponse = orderResponses.get(position);
        holder.binding.txtGioDat.setText(DateTimeUtil.formatTime(orderResponse.getOrder().getCreatedAt()));
        holder.binding.txtNgayDat.setText(DateTimeUtil.formatDate(orderResponse.getOrder().getCreatedAt()));

        String amount = DateTimeUtil.formatCurrency(orderResponse.getOrder().getAmount().toString()) + " đ";
        holder.binding.txtTongTien.setText(amount);

        int number = 0;
        for (OrderItemResponse orderItemResponse : orderResponse.getOrderItems())
            number += orderItemResponse.getAmount();
        String numberFood = number + " món được đặt";
        holder.binding.txtSoMon.setText(numberFood);

        String orderId = "Đơn hàng " + orderResponse.getOrder().getOrderId();
        holder.binding.textView.setText(orderId);
    }

    @Override
    public int getItemCount() {
        return orderResponses == null ? 0 : orderResponses.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding binding;

        public OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
