package com.theduckfood.theduckfoodpartner.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.theduckfoodpartner.R;
import com.theduckfood.theduckfoodpartner.adapter.OrderItemAdapter;
import com.theduckfood.theduckfoodpartner.databinding.FragmentNewOrdersShipperBinding;
import com.theduckfood.theduckfoodpartner.databinding.FragmentOrderCurrentShipperBinding;
import com.theduckfood.theduckfoodpartner.model.response.DeliveryGetOrdersResponse;
import com.theduckfood.theduckfoodpartner.presenter.OrderPresenter;
import com.theduckfood.theduckfoodpartner.presenter.contact.IOrderView;
import com.theduckfood.theduckfoodpartner.util.Constant;

import java.util.ArrayList;

public class OrderCurrentFragment extends Fragment implements IOrderView {
    FragmentOrderCurrentShipperBinding binding;
    Dialog dialog;
    OrderItemAdapter adapter;
    OrderPresenter orderPresenter;
    public OrderCurrentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderCurrentShipperBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initDialog();
        orderPresenter = new OrderPresenter(this, getContext());
        orderPresenter.getOrders(Constant.ORDER_TYPE_CURRENT);
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new OrderItemAdapter(getContext(), new ArrayList<>());
        binding.listItem.setAdapter(adapter);
        binding.listItem.setLayoutManager(
                new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
    }

    private void initDialog() {
        this.dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void getOrdersResponse(DeliveryGetOrdersResponse response) {
        loading(false);
        if (response == null || response.isError()) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        if (response.getOrders().size() == 0) {
            binding.layoutNotFoundOrder.setVisibility(View.VISIBLE);
            binding.listItem.setVisibility(View.GONE);
            return;
        }

        binding.listItem.setVisibility(View.VISIBLE);
        binding.layoutNotFoundOrder.setVisibility(View.GONE);
        adapter.setOrderResponses(response.getOrders());
    }

    @Override
    public void loading(boolean isLoading) {
        if (isLoading)
            dialog.show();
        else
            dialog.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        orderPresenter.getOrders(Constant.ORDER_TYPE_CURRENT);
    }
}