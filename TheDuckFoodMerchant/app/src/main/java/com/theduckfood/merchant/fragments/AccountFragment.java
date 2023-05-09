package com.theduckfood.merchant.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {
    private static AccountFragment instance;
    private FragmentAccountBinding binding;

    public static AccountFragment newInstance() {
        return instance == null ? new AccountFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}