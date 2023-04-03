package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theduckfood.R;
import com.theduckfood.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.txtLogin.setOnClickListener(v -> onBackPressed());
    }
}