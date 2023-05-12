package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityWalletBinding;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}