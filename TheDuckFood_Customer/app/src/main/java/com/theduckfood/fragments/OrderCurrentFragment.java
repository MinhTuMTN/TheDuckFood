package com.theduckfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.R;
import com.theduckfood.adapter.OrderListAdapter;
import com.theduckfood.databinding.FragmentOrderCurrentBinding;
import com.theduckfood.model.respone.GetOrdersResponse;
import com.theduckfood.model.respone.OrderItemResponse;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.presenter.GetOrdersPresenter;
import com.theduckfood.presenter.contact.IGetOrdersView;
import com.theduckfood.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderCurrentFragment extends Fragment implements IGetOrdersView{
    FragmentOrderCurrentBinding binding;
    OrderListAdapter orderListAdapter;

    List<OrderResponse> currentOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderCurrentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetOrdersPresenter getOrdersPresenter = new GetOrdersPresenter(getActivity(), this);
        getOrdersPresenter.getAllOrders();

    }

    @Override
    public void getAllOrders(GetOrdersResponse getOrdersResponse) {
        if (getOrdersResponse == null) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        getCurrentOrders(getOrdersResponse);

        if (currentOrders != null && currentOrders.size() != 0){
            binding.recyclerCurrentOrders.setVisibility(View.VISIBLE);
            binding.layoutNotFoundOrder.setVisibility(View.GONE);
        } else return;

        orderListAdapter = new OrderListAdapter(getContext(), currentOrders);
        binding.recyclerCurrentOrders.setAdapter(orderListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerCurrentOrders.setLayoutManager(linearLayoutManager);
    }

    private void getCurrentOrders(@NonNull GetOrdersResponse getOrdersResponse) {
        currentOrders = new ArrayList<>();
        for (OrderResponse orderResponse : getOrdersResponse.getOrders()){
            if (!orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_SUCCESS) && !orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_USER_CANCELED)) {
                currentOrders.add(orderResponse);
            }
        }
    }
}
