package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.theduckfood.R;
import com.theduckfood.adapter.OrderDetailListAdapter;
import com.theduckfood.adapter.OrderPaymentAdapter;
import com.theduckfood.databinding.ActivityOrderPaymentBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Store;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.List;

public class OrderPaymentActivity extends AppCompatActivity {
    ActivityOrderPaymentBinding binding;

    OrderPaymentAdapter orderPaymentAdapter;

    Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataIntent();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnAddMoreFoods.setOnClickListener(v -> onBackPressed());
    }

    private void getDataIntent() {
        store = (Store) getIntent().getSerializableExtra("store");
    }

    private void loadData() {
        binding.txtTenQuan.setText(store.getStoreName());

        getOrderItemList();
    }

    private void getOrderItemList() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        List<CartItem> cartItems = sharedPreferenceManager.getCartItems();
        orderPaymentAdapter = new OrderPaymentAdapter(this, cartItems);
        binding.recyclerFoods.setAdapter(orderPaymentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerFoods.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}