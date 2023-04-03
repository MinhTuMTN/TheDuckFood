package com.theduckfood.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theduckfood.R;
import com.theduckfood.activities.LoginActivity;
import com.theduckfood.databinding.FragmentEnterEmailBinding;


public class EnterEmailFragment extends Fragment {
    private static EnterEmailFragment instance;
    private FragmentEnterEmailBinding binding;

    public static EnterEmailFragment newInstance() {
        return instance == null ? new EnterEmailFragment() : instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEnterEmailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        addEvents();
    }

    private void addEvents() {
        binding.btnBack2.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        });

        binding.btnNext.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new EnterOTPFragment())
                    .addToBackStack("EnterOTP")
                    .commit();
        });
    }
}