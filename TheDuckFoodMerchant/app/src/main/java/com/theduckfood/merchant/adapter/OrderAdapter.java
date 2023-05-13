package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.merchant.activities.OrderDetailsActivity;
import com.theduckfood.merchant.databinding.ItemOrderBinding;
import com.theduckfood.merchant.model.response.OrderItemResponse;
import com.theduckfood.merchant.model.response.StoreOrderResponse;
import com.theduckfood.merchant.util.Constant;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    List<StoreOrderResponse> orderResponses;
    String storeName;

    public OrderAdapter(Context context, List<StoreOrderResponse> orderResponses, String storeName) {
        this.context = context;
        this.orderResponses = orderResponses;
        this.storeName = storeName;
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
        final int numFood = number;
        String numberFood = number + " món được đặt";
        holder.binding.txtSoMon.setText(numberFood);

        String orderId = "Đơn hàng " + orderResponse.getOrder().getOrderId();
        holder.binding.txtMaDonHang.setText(orderId);

        String orderStatus = "";
        switch (orderResponse.getOrder().getStatus()) {
            case Constant.ORDER_STATUS_SUCCESS:
                orderStatus = "Đã đến nơi";
                break;
            case Constant.ORDER_STATUS_USER_CANCELED:
                orderStatus = "Bị hủy";
                break;
            case Constant.ORDER_STATUS_PROCESSING:
                orderStatus = "Đang chuẩn bị";
                break;
            default:
                orderStatus = "Đang giao hàng";
                break;
        }
        holder.binding.txtTrangThaiDonHang.setText(orderStatus);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), OrderDetailsActivity.class);
            intent.putExtra("order", orderResponse);
            intent.putExtra("storeName", this.storeName);
            intent.putExtra("amountFood", numFood);
            v.getContext().startActivity(intent);
        });
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
