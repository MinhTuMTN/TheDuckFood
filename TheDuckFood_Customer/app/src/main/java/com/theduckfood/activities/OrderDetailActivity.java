package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.OrderDetailListAdapter;
import com.theduckfood.databinding.ActivityOrderDetailBinding;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;

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
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> switchToMainActivity());
    }

    private void loadOrderDetail() {
        OrderResponse orderDetail = (OrderResponse) getIntent().getSerializableExtra("orderDetail");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binding.txtDate.setText(dateFormat.format(orderDetail.getOrder().getCreatedAt()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        binding.txtTime.setText(timeFormat.format(orderDetail.getOrder().getCreatedAt()));

        String amount = Math.round(orderDetail.getOrder().getAmount()) + " đ";
        binding.txtPrice.setText(amount);

        String shipFee = Math.round(orderDetail.getOrder().getShipFee()) + " đ";
        binding.txtShipFee.setText(shipFee);

        String serviceFee = Math.round(orderDetail.getOrder().getServiceFee()) + " đ";
        binding.txtServiceFee.setText(serviceFee);

        String total = Math.round(orderDetail.getOrder().getAmount() + orderDetail.getOrder().getShipFee() + orderDetail.getOrder().getServiceFee()) + " đ";
        binding.txtTotalPrice.setText(total);

        binding.txtStoreName.setText(orderDetail.getStore().getStoreName());

        binding.txtStoreAddress.setText(orderDetail.getStore().getStoreAddress());

        binding.txtUserAddress.setText(orderDetail.getAddress());

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

    public void switchToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
