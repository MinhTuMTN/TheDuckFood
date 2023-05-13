package com.theduckfood.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.OrderDetailListAdapter;
import com.theduckfood.databinding.ActivityOrderDetailBinding;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;

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
//        addEvents(updateProfilePresenter);
    }

    private void loadOrderDetail() {
        OrderResponse orderDetail = (OrderResponse) getIntent().getSerializableExtra("orderDetail");

        String amount = Math.round(orderDetail.getOrder().getAmount()) + " đ";
        binding.txtPrice.setText(amount);

        String shipFee = Math.round(orderDetail.getOrder().getShipFee()) + " đ";
        binding.txtShipFee.setText(shipFee);

        String serviceFee = Math.round(orderDetail.getOrder().getServiceFee()) + " đ";
        binding.txtServiceFee.setText(serviceFee);

        String total = Math.round(orderDetail.getOrder().getAmount() + orderDetail.getOrder().getShipFee() + orderDetail.getOrder().getServiceFee()) + " đ";
        binding.txtTotalPrice.setText(total);
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
}
