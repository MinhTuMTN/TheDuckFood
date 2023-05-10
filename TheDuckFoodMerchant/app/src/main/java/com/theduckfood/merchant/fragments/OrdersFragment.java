package com.theduckfood.merchant.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.FragmentOrdersBinding;
public class OrdersFragment extends Fragment {
    private static OrdersFragment instance;
    private FragmentOrdersBinding binding;
    public static OrdersFragment newInstance() {
        return instance == null ? new OrdersFragment() : instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}