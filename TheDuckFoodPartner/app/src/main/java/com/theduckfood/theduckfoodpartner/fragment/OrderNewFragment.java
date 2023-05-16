package com.theduckfood.theduckfoodpartner.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theduckfood.theduckfoodpartner.databinding.FragmentNewOrdersShipperBinding;

public class OrderNewFragment extends Fragment {
    FragmentNewOrdersShipperBinding binding;
    public OrderNewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewOrdersShipperBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}