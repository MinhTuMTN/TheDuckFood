package com.theduckfood.theduckfoodpartner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.theduckfood.theduckfoodpartner.adapter.ViewOrderAdapter;
import com.theduckfood.theduckfoodpartner.databinding.ActivityMainBinding;
import com.theduckfood.theduckfoodpartner.util.SharedPreferenceManager;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadDeliveryManName();
        handleFragments();
    }

    private void handleFragments() {
        ViewOrderAdapter viewOrderAdapter = new ViewOrderAdapter(this);
        binding.viewPager.setAdapter(viewOrderAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Nhận đơn");
                            break;
                        case 1:
                            tab.setText("Hiện tại");
                            break;
                        default:
                            tab.setText("Lịch sử");
                            break;
                    }
                }
        ).attach();
    }

    private void loadDeliveryManName() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        String name = sharedPreferenceManager.getStringValue(SharedPreferenceManager.USER_PROFILE_FULL_NAME_KEY) + "!";
        binding.txtTen.setText(name);
    }
}