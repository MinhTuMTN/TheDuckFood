package com.theduckfood.theduckfoodpartner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.theduckfoodpartner.R;
import com.theduckfood.theduckfoodpartner.databinding.ItemNewOrderShipperBinding;
import com.theduckfood.theduckfoodpartner.model.response.DeliveryOrderResponse;
import com.theduckfood.theduckfoodpartner.presenter.OrderPresenter;
import com.theduckfood.theduckfoodpartner.util.Constant;
import com.theduckfood.theduckfoodpartner.util.DateTimeUtil;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>{
    Context context;
    List<DeliveryOrderResponse> orderResponses;
    OrderPresenter orderPresenter;

    public OrderItemAdapter(Context context, List<DeliveryOrderResponse> orderResponses, OrderPresenter orderPresenter) {
        this.context = context;
        this.orderResponses = orderResponses;
        this.orderPresenter = orderPresenter;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(ItemNewOrderShipperBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        DeliveryOrderResponse orderResponse = orderResponses.get(position);
        holder.binding.txtTenKhachHang.setText(orderResponse.getUserName());
        holder.binding.txtTenQuanAn.setText(orderResponse.getStoreName());
        holder.binding.txtTongHoaDon.setText(DateTimeUtil.formatCurrency(String.valueOf(orderResponse.getOrder().getAmount())));

        String status = "";
        holder.binding.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.order_not_cancel));
        holder.binding.imageView.setImageResource(R.drawable.record);
        holder.binding.btnHuyBo.setOnClickListener(v -> {
            String type = orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_WAITING)
                    ? Constant.ORDER_TYPE_NEW
                    : Constant.ORDER_TYPE_CURRENT;
            orderPresenter.updateOrder(
                    orderResponse.getOrder().getOrderId(),
                    Constant.ORDER_STATUS_USER_CANCELED,
                    type
            );
        });
        switch (orderResponse.getOrder().getStatus()) {
            case Constant.ORDER_STATUS_WAITING:
                holder.binding.btnHuyBo.setVisibility(View.GONE);
                status = "Chờ xác nhận";
                holder.binding.btnChapNhan.setOnClickListener(v -> {
                    orderPresenter.updateOrder(
                            orderResponse.getOrder().getOrderId(),
                            Constant.ORDER_STATUS_PROCESSING,
                            Constant.ORDER_TYPE_NEW
                            );
                });
                break;
            case Constant.ORDER_STATUS_PROCESSING:
                status = "Đang chuẩn bị";
                holder.binding.txtChapNhan.setText("Giao hàng");
                holder.binding.btnChapNhan.setOnClickListener(v -> {
                    orderPresenter.updateOrder(
                            orderResponse.getOrder().getOrderId(),
                            Constant.ORDER_STATUS_SHIPPING,
                            Constant.ORDER_TYPE_CURRENT
                    );
                });
                break;
            case Constant.ORDER_STATUS_SHIPPING:
                status = "Đang giao";
                holder.binding.txtChapNhan.setText("Đã đến nơi");
                holder.binding.btnChapNhan.setOnClickListener(v -> {
                    orderPresenter.updateOrder(
                            orderResponse.getOrder().getOrderId(),
                            Constant.ORDER_STATUS_SUCCESS,
                            Constant.ORDER_TYPE_CURRENT
                    );
                });
                break;
            case Constant.ORDER_STATUS_SUCCESS:
                status = "Thành công";
                holder.binding.btnHuyBo.setVisibility(View.GONE);
                holder.binding.btnChapNhan.setVisibility(View.GONE);
                break;
            case Constant.ORDER_STATUS_USER_CANCELED:
                status = "Bị hủy";
                holder.binding.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.red_pink_1));
                holder.binding.imageView.setImageResource(R.drawable.cancel);
                holder.binding.btnHuyBo.setVisibility(View.GONE);
                holder.binding.btnChapNhan.setVisibility(View.GONE);
                break;
        }
        holder.binding.txtTrangThai.setText(status);
    }

    @Override
    public int getItemCount() {
        return orderResponses.size();
    }

    public void setOrderResponses(List<DeliveryOrderResponse> orderResponses) {
        this.orderResponses.clear();
        this.orderResponses = orderResponses;
        notifyDataSetChanged();
    }
    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        ItemNewOrderShipperBinding binding;


        public OrderItemViewHolder(ItemNewOrderShipperBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
