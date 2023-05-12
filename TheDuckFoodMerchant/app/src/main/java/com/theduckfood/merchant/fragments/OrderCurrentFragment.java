package com.theduckfood.merchant.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.merchant.adapter.OrderAdapter;
import com.theduckfood.merchant.databinding.FragmentCurrentOrderBinding;
import com.theduckfood.merchant.model.response.StoreGetOrdersResponse;
import com.theduckfood.merchant.presenter.OrderPresenter;
import com.theduckfood.merchant.presenter.contact.IOrderView;
import com.theduckfood.merchant.util.Constant;


public class OrderCurrentFragment extends Fragment implements IOrderView {
    FragmentCurrentOrderBinding binding;
    OrderPresenter orderPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCurrentOrderBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderPresenter = new OrderPresenter(this, getContext());
        orderPresenter.getOrders(Constant.ORDER_STATUS_PROCESSING);
    }

    @Override
    public void getOrdersResponse(StoreGetOrdersResponse ordersResponse) {
        if (ordersResponse == null || ordersResponse.isError()) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.listDonHang.setVisibility(View.GONE);
        binding.imgKhongDonHang.setVisibility(View.VISIBLE);
        if (ordersResponse.getOrders() == null || ordersResponse.getOrders().size() == 0)
            return;
        OrderAdapter orderAdapter = new OrderAdapter(
                getContext(),
                ordersResponse.getOrders(),
                ordersResponse.getStoreName());
        binding.listDonHang.setAdapter(orderAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        binding.listDonHang.setLayoutManager(linearLayoutManager);
    }
}