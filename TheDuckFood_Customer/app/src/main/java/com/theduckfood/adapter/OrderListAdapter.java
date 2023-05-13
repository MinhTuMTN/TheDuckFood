package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.R;
import com.theduckfood.activities.OrderDetailActivity;
import com.theduckfood.databinding.ItemOrderBinding;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.util.Constant;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {
    ItemOrderBinding binding;
    Context context;
    List<OrderResponse> orderResponses;

    public OrderListAdapter(Context context, List<OrderResponse> orderResponses) {
        this.context = context;
        this.orderResponses = orderResponses;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderResponse orderResponse = orderResponses.get(position);

        String orderId = "Mã đơn hàng: " + orderResponse.getOrder().getOrderId();
        holder.binding.txtOrderId.setText(orderId);

        String price = Math.round(orderResponse.getOrder().getAmount()) + " đ";
        holder.binding.txtPrice.setText(price);

        int amount = 0;
        String amountFood;
        if (orderResponse.getOrderItems() != null && orderResponse.getOrderItems().size() != 0) {
            for (OrderItemResponse orderItemResponse : orderResponse.getOrderItems())
                amount += orderItemResponse.getAmount();
        }
        amountFood = "Đã đặt " + amount + " món";
        holder.binding.txtAmount.setText(amountFood);

        if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_SUCCESS)) {
            holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icons8_success_14px, 0, 0, 0);
            holder.binding.txtStatus.setText("Đã nhận");
            holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.green_dark));
        } else if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_USER_CANCELED)) {
            holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icons8_cancelled_14px, 0, 0, 0);
            holder.binding.txtStatus.setText("Đã hủy");
            holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_SHIPPING)) {
            holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icons8_shipping_14px, 0, 0, 0);
            holder.binding.txtStatus.setText("Đang giao");
            holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow_dark));
        } else if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_PROCESSING)) {
            holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icons8_process_14px, 0, 0, 0);
            holder.binding.txtStatus.setText("Chờ tiếp nhận");
            holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow_dark));
        } else if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_WAITING)) {
            holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icons8_waiting_14px, 0, 0, 0);
            holder.binding.txtStatus.setText("Chờ shipper");
            holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow_dark));
        }

        holder.binding.btnDetail.setOnClickListener(v -> orderDetail(v, orderResponse));
    }

    private void orderDetail(View view, OrderResponse orderResponse) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("orderDetail", orderResponse);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return orderResponses == null ? 0 : orderResponses.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        ItemOrderBinding binding;

        public OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
