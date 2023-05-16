package com.theduckfood.theduckfoodpartner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.theduckfood.theduckfoodpartner.databinding.FragmentNewOrdersShipperBinding;
import com.theduckfood.theduckfoodpartner.databinding.FragmentOrderCurrentShipperBinding;

public class OrderCurrentFragment extends Fragment {
    FragmentOrderCurrentShipperBinding binding;
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
}