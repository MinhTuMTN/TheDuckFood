package com.theduckfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theduckfood.R;
import com.theduckfood.databinding.FragmentEnterOtpBinding;

import java.util.Objects;

public class EnterOTPFragment extends Fragment {
    private FragmentEnterOtpBinding binding;
    private static EnterOTPFragment instance;

    public static EnterOTPFragment newInstance() {
        return instance == null ? new EnterOTPFragment() : instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEnterOtpBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addEvents();
    }

    private void addEvents() {
        binding.btnBack2.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .popBackStack());

        binding.btnNext.setOnClickListener(v -> verifyOTP());
    }

    private void verifyOTP() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, EnterNewPasswordFragment.newInstance())
                .addToBackStack("EnterNewPassword")
                .commit();
    }
}