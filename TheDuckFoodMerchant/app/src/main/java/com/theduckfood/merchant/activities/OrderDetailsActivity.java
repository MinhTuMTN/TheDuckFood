package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.adapter.OrderItemAdapter;
import com.theduckfood.merchant.databinding.ActivityOrderDetailsBinding;
import com.theduckfood.merchant.model.response.StoreOrderResponse;
import com.theduckfood.merchant.util.Constant;
import com.theduckfood.merchant.util.DateTimeUtil;

public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadData() {
        StoreOrderResponse orderResponse = (StoreOrderResponse) getIntent().getSerializableExtra("order");
        String storeName = getIntent().getStringExtra("storeName");
        int amountFood = getIntent().getIntExtra("amountFood", 0);
        binding.txtStoreName.setText(storeName);

        binding.txtMaDonHang.setText(String.valueOf(orderResponse.getOrder().getOrderId()));
        binding.txtTenKhachHang.setText(orderResponse.getUserName());
        binding.txtTenTaiXe.setText(orderResponse.getDeliverManName());

        String soMon = amountFood + " món";
        binding.txtSoMon.setText(soMon);
        binding.txtTongTamTinh.setText(
                DateTimeUtil
                    .formatCurrency(
                        String.valueOf(
                                orderResponse.getOrder().getAmount()
                                        + orderResponse.getOrder().getDiscountAmount()
                        )
                )
        );
        String discount = "-" + DateTimeUtil.formatCurrency(
                orderResponse.getOrder().getDiscountAmount().toString()
        );
        binding.txtTienGiamCoupon.setText(discount);
        binding.txtTongSauCung.setText(DateTimeUtil
                .formatCurrency(String.valueOf(orderResponse.getOrder().getAmount() - Constant.SERVICE_FEE)));

        OrderItemAdapter adapter = new OrderItemAdapter(this, orderResponse.getOrderItems());
        binding.listOrderItem.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.listOrderItem.setLayoutManager(linearLayoutManager);
    }
}