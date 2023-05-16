package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

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
        binding.btnBack.setOnClickListener(v -> switchToStoreDetailActivity(store.getStoreId()));
        binding.btnAddMoreFoods.setOnClickListener(v -> switchToStoreDetailActivity(store.getStoreId()));
        binding.btnChangeAddress.setOnClickListener(v -> switchToUserAddressActivity());
        binding.cardCoupon.setOnClickListener(v -> switchToUseCouponActivity(store.getStoreId()));
    }

    private void switchToUseCouponActivity(Long storeId) {
        Intent intent = new Intent(this, UseCouponActivity.class);
        intent.putExtra("storeId", storeId);
        startActivity(intent);
    }

    private void switchToUserAddressActivity() {
        Intent intent = new Intent(this, UserAddressActivity.class);
        startActivity(intent);
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
        orderPaymentAdapter = new OrderPaymentAdapter(this, cartItems, store.getStoreId());
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

    private void switchToStoreDetailActivity(Long storeId) {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("store", storeId);
        startActivity(intent);
    }
}