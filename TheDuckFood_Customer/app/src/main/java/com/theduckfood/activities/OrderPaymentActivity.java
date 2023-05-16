package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.theduckfood.R;
import com.theduckfood.databinding.ActivityOrderPaymentBinding;

public class OrderPaymentActivity extends AppCompatActivity {
    ActivityOrderPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}