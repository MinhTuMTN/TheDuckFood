package com.theduckfood.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.theduckfood.activities.MainActivity;
import com.theduckfood.adapter.OrderFragmentAdapter;
import com.theduckfood.databinding.FragmentOrdersBinding;

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
        OrderFragmentAdapter orderFragmentAdapter = new OrderFragmentAdapter(this);
        binding.viewPager.setAdapter(orderFragmentAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Hiện tại");
                            break;
                        default:
                            tab.setText("Lịch sử");
                            break;
                    }
                }
        ).attach();
        binding.btnBack.setOnClickListener(v -> switchToMainActivity());
    }

    public void switchToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
