package com.theduckfood.theduckfoodpartner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.theduckfood.theduckfoodpartner.databinding.FragmentNewOrdersShipperBinding;
import com.theduckfood.theduckfoodpartner.databinding.FragmentOrderHistoryShipperBinding;

public class OrderHistoryFragment extends Fragment {
    FragmentOrderHistoryShipperBinding binding;
    public OrderHistoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryShipperBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}