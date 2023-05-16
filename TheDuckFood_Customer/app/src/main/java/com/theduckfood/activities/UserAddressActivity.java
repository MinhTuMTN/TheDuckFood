package com.theduckfood.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.AddressListAdapter;
import com.theduckfood.databinding.ActivityUseAddressBinding;
import com.theduckfood.model.UserAddress;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.UserAddressResponse;
import com.theduckfood.presenter.UserAddressPresenter;
import com.theduckfood.presenter.contact.IUserAddressView;

import java.util.ArrayList;
import java.util.List;

public class UserAddressActivity extends AppCompatActivity implements IUserAddressView {
    ActivityUseAddressBinding binding;
    AddressListAdapter addressListAdapter;
    UserAddressPresenter userAddressPresenter;
    List<UserAddress> userAddresses;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUseAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userAddressPresenter = new UserAddressPresenter(this, UserAddressActivity.this);
        userAddressPresenter.getUserAddress();
        init();
        loadData();
        addEvents();
    }

    private void init() {

    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnAdd.setOnClickListener(v -> onAddButtonClicked());
    }

    private void onAddButtonClicked() {
        String streetAddress = binding.edtAddress.getText().toString();
        userAddressPresenter.addUserAddress(streetAddress);
    }

    private void loadData() {
        addressListAdapter = new AddressListAdapter(this, new ArrayList<>());
        binding.recyclerAddress.setAdapter(addressListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerAddress.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void addUserAddress(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserAddress(UserAddressResponse userAddressResponse) {
        if (userAddressResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            binding.recyclerAddress.setVisibility(View.GONE);
            binding.constraintKhongTimThay.setVisibility(View.VISIBLE);
            return;
        }

        if (userAddressResponse.getUserAddresses().isEmpty()) {
            binding.recyclerAddress.setVisibility(View.GONE);
            binding.constraintKhongTimThay.setVisibility(View.VISIBLE);
            return;
        }

        binding.recyclerAddress.setVisibility(View.VISIBLE);
        binding.constraintKhongTimThay.setVisibility(View.GONE);

        userAddresses = userAddressResponse.getUserAddresses();
        addressListAdapter.setUserAddresses(userAddresses);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public void deleteUserAddress(UserAddressResponse userAddressResponse) {

    }
}
