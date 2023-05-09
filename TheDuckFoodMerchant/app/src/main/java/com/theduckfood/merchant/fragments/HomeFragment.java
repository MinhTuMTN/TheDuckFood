package com.theduckfood.merchant.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private static HomeFragment instance;
    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {
        return instance == null ? new HomeFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}