package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.AddressListAdapter;
import com.theduckfood.databinding.ActivityUseAddressBinding;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.UserAddressResponse;
import com.theduckfood.presenter.UserAddressPresenter;
import com.theduckfood.presenter.contact.IUserAddressView;

public class UserAddressActivity extends AppCompatActivity implements IUserAddressView {
    ActivityUseAddressBinding binding;
    AddressListAdapter addressListAdapter;
    UserAddressPresenter userAddressPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUseAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userAddressPresenter = new UserAddressPresenter(this, UserAddressActivity.this);
        userAddressPresenter.getUserAddress();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadData() {

    }

    @Override
    public void addUserAddress(SimpleMessageResponse simpleMessageResponse) {

    }

    @Override
    public void getUserAddress(UserAddressResponse userAddressResponse) {
        if (userAddressResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            binding.recyclerAddress.setVisibility(View.GONE);
            binding.constraintKhongTimThay.setVisibility(View.VISIBLE);
            return;
        }
        binding.recyclerAddress.setVisibility(View.VISIBLE);
        binding.constraintKhongTimThay.setVisibility(View.GONE);

        addressListAdapter = new AddressListAdapter(this, userAddressResponse.getUserAddresses());
        binding.recyclerAddress.setAdapter(addressListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerAddress.setLayoutManager(linearLayoutManager);
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
