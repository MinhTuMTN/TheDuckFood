package com.theduckfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theduckfood.adapter.OrderListAdapter;
import com.theduckfood.databinding.FragmentOrderHistoryBinding;
import com.theduckfood.model.respone.GetOrdersResponse;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.presenter.GetOrdersPresenter;
import com.theduckfood.presenter.contact.IGetOrdersView;
import com.theduckfood.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryFragment extends Fragment implements IGetOrdersView {
    FragmentOrderHistoryBinding binding;

    OrderListAdapter orderListAdapter;

    List<OrderResponse> historyOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(getLayoutInflater());
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
            binding.recyclerHistoryOrders.setVisibility(View.GONE);
            binding.layoutNotFoundOrder.setVisibility(View.VISIBLE);
            return;
        }

        getHistoryOrders(getOrdersResponse);

        if (historyOrders == null || historyOrders.size() == 0){
            binding.recyclerHistoryOrders.setVisibility(View.GONE);
            binding.layoutNotFoundOrder.setVisibility(View.VISIBLE);
            return;
        }

        orderListAdapter = new OrderListAdapter(getContext(), historyOrders);
        binding.recyclerHistoryOrders.setAdapter(orderListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerHistoryOrders.setLayoutManager(linearLayoutManager);
    }

    private void getHistoryOrders(@NonNull GetOrdersResponse getOrdersResponse) {
        historyOrders = new ArrayList<>();
        for (OrderResponse orderResponse : getOrdersResponse.getOrders()){
            if (orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_SUCCESS) || orderResponse.getOrder().getStatus().equals(Constant.ORDER_STATUS_USER_CANCELED)) {
                historyOrders.add(orderResponse);
            }
        }
    }
}
