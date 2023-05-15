package com.theduckfood.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.OrderDetailListAdapter;
import com.theduckfood.databinding.ActivityOrderDetailBinding;
import com.theduckfood.model.Order;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    ActivityOrderDetailBinding binding;
    OrderDetailListAdapter orderDetailListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadOrderDetail();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void loadOrderDetail() {
        OrderResponse orderDetail = (OrderResponse) getIntent().getSerializableExtra("orderDetail");

        String orderId = "Mã đơn hàng: " + orderDetail.getOrder().getOrderId().toString();
        binding.txtOrderId.setText(orderId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binding.txtDate.setText(dateFormat.format(orderDetail.getOrder().getCreatedAt()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        binding.txtTime.setText(timeFormat.format(orderDetail.getOrder().getCreatedAt()));

        String amount = DateTimeUtil.formatCurrency(String.valueOf(orderDetail.getOrder().getAmount())) + " đ";
        binding.txtPrice.setText(amount);

        String shipFee = DateTimeUtil.formatCurrency(String.valueOf(orderDetail.getOrder().getShipFee())) + " đ";
        binding.txtShipFee.setText(shipFee);

        String serviceFee = DateTimeUtil.formatCurrency(String.valueOf(orderDetail.getOrder().getServiceFee())) + " đ";
        binding.txtServiceFee.setText(serviceFee);

        String total = DateTimeUtil.formatCurrency(
                String.valueOf(
                        orderDetail.getOrder().getAmount()
                                + orderDetail.getOrder().getShipFee()
                                + orderDetail.getOrder().getServiceFee()
                )
        ) + " đ";
        binding.txtTotalPrice.setText(total);

        binding.txtStoreName.setText(orderDetail.getStore().getStoreName());

        binding.txtStoreAddress.setText(orderDetail.getStore().getStoreAddress());

        if (orderDetail.getReview() == null) {
            if (orderDetail.getOrder().getStatus().equals(Constant.ORDER_STATUS_SUCCESS)) {
                binding.ratingBarReview.setOnTouchListener((v, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        switchToUserReviewActivity(orderDetail);
                    }
                    return true;
                });
            } else {
                binding.txtReviewLabel.setText("Bạn không thể đánh giá đơn hàng này");
            }
        } else {
            binding.ratingBarReview.setRating(orderDetail.getReview().getRate());
            binding.txtReviewLabel.setText("Cảm ơn! Bạn đã đánh giá đơn hàng này!");
        }
        binding.txtUserAddress.setText(orderDetail.getAddress());

        binding.btnBack.setOnClickListener(v -> switchToMainActivity());

        if (orderDetail.getOrderItems() == null || orderDetail.getOrderItems().size() == 0)
            return;

        getOrderDetailList(orderDetail.getOrderItems());
    }

    private void getOrderDetailList(List<OrderItemResponse> orderItems) {
        orderDetailListAdapter = new OrderDetailListAdapter(this, orderItems);
        binding.recyclerOrderDetailList.setAdapter(orderDetailListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerOrderDetailList.setLayoutManager(linearLayoutManager);
    }

    private void switchToUserReviewActivity(OrderResponse orderDetail) {
        Intent intent = new Intent(this, UserReviewActivity.class);
        intent.putExtra("orderDetail", orderDetail);
        startActivity(intent);
    }

    public void switchToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
