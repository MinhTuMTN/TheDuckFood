package com.theduckfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.databinding.ItemOrderDetailBinding;
import com.theduckfood.model.respone.OrderItemResponse;

import java.util.List;

public class OrderDetailListAdapter extends RecyclerView.Adapter<OrderDetailListAdapter.OrderListViewHolder>{
    ItemOrderDetailBinding binding;
    Context context;
    List<OrderItemResponse> orderItemResponses;

    public OrderDetailListAdapter(Context context, List<OrderItemResponse> orderItemResponses) {
        this.context = context;
        this.orderItemResponses = orderItemResponses;
    }

    @NonNull
    @Override
    public OrderDetailListAdapter.OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderDetailListAdapter.OrderListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailListAdapter.OrderListViewHolder holder, int position) {
        OrderItemResponse orderItemResponse = orderItemResponses.get(position);

        holder.binding.txtFoodName.setText(orderItemResponse.getFoodName());

        String amount = "x" + orderItemResponse.getAmount();
        holder.binding.txtAmount.setText(amount);
    }

    @Override
    public int getItemCount() {
        return orderItemResponses == null ? 0 : orderItemResponses.size();
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder{
        ItemOrderDetailBinding binding;

        public OrderListViewHolder(ItemOrderDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
