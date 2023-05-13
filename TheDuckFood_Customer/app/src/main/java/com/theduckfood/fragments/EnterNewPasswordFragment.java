package com.theduckfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theduckfood.databinding.FragmentEnterNewPasswordBinding;

public class EnterNewPasswordFragment extends Fragment {
    FragmentEnterNewPasswordBinding binding;
    private static EnterNewPasswordFragment instance;

    public static EnterNewPasswordFragment newInstance() {
        return instance == null ? new EnterNewPasswordFragment() : instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEnterNewPasswordBinding.inflate(getLayoutInflater());
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
        binding.materialButton.setOnClickListener(v -> changePassword());
    }

    private void changePassword() {
    }
}