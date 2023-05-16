package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.R;
import com.theduckfood.activities.MainActivity;
import com.theduckfood.activities.OrderDetailActivity;
import com.theduckfood.databinding.ItemOrderBinding;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;

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

        String price = DateTimeUtil.formatCurrency(String.valueOf(orderResponse.getOrder().getAmount())) + " đ";
        holder.binding.txtPrice.setText(price);

        int amount = 0;
        String amountFood;
        if (orderResponse.getOrderItems() != null && orderResponse.getOrderItems().size() != 0) {
            for (OrderItemResponse orderItemResponse : orderResponse.getOrderItems())
                amount += orderItemResponse.getAmount();
        }
        amountFood = amount + " món";
        holder.binding.txtAmount.setText(amountFood);

        String status;
        int leftDrawable;
        int textColor;

        switch (orderResponse.getOrder().getStatus()){
            case Constant.ORDER_STATUS_SUCCESS:
                status = "Đã đến nơi";
                leftDrawable = R.drawable.icons8_success_14px;
                textColor = R.color.green_dark;
                break;
            case Constant.ORDER_STATUS_USER_CANCELED:
                status = "Bị hủy";
                leftDrawable = R.drawable.icons8_cancelled_14px;
                textColor = R.color.red;
                break;
            case Constant.ORDER_STATUS_SHIPPING:
                status = "Đang giao hàng";
                leftDrawable = R.drawable.icons8_shipping_14px;
                textColor = R.color.yellow_dark;
                break;
            case Constant.ORDER_STATUS_PROCESSING:
                status = "Đang chuẩn bị";
                leftDrawable = R.drawable.icons8_process_14px;
                textColor = R.color.yellow_dark;
                break;
            default:
                status = "Chờ shipper";
                leftDrawable = R.drawable.icons8_waiting_14px;
                textColor = R.color.yellow_dark;
                break;
        }

        holder.binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, 0, 0);
        holder.binding.txtStatus.setText(status);
        holder.binding.txtStatus.setTextColor(ContextCompat.getColor(context, textColor));

        holder.itemView.setOnClickListener(v -> orderDetail(v, orderResponse));
        holder.binding.txtStoreName.setText(orderResponse.getStore().getStoreName());
        holder.binding.txtDate.setText(DateTimeUtil.formatDate(orderResponse.getOrder().getCreatedAt()));
        holder.binding.txtTime.setText(DateTimeUtil.formatTime(orderResponse.getOrder().getCreatedAt()));

    }

    private void orderDetail(View view, OrderResponse orderResponse) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
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
