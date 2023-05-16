package com.theduckfood.theduckfoodpartner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }

    private void loadDeliveryManName() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        String name = sharedPreferenceManager.getStringValue(SharedPreferenceManager.USER_PROFILE_FULL_NAME_KEY) + "!";
        binding.txtTen.setText(name);
    }
}