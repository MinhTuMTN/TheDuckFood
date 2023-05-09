package com.theduckfood.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.theduckfood.R;
import com.theduckfood.activities.LoginActivity;
import com.theduckfood.databinding.FragmentProfileBinding;
import com.theduckfood.databinding.PopupLogoutConfirmBinding;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        loadUserData(view);
        addEvents();
    }

    private void addEvents() {
        binding.cardLogOut.setOnClickListener(this::logOut);
    }

    private void logOut(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        PopupLogoutConfirmBinding popupLogoutConfirmBinding = PopupLogoutConfirmBinding.inflate(LayoutInflater.from(view.getContext()));
        dialog.setContentView(popupLogoutConfirmBinding.getRoot());

        popupLogoutConfirmBinding.btnClose.setOnClickListener(v -> dialog.dismiss());
        popupLogoutConfirmBinding.btnLogout.setOnClickListener(v -> {
            new SharedPreferenceManager(v.getContext()).clear();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            requireActivity().startActivity(intent);
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void loadUserData(View view) {
        SharedPreferenceManager preferenceManager = new SharedPreferenceManager(view.getContext());
        binding.txtFullName.setText(preferenceManager.getStringValue(SharedPreferenceManager.USER_PROFILE_FULL_NAME_KEY));
        binding.txtEmail.setText(preferenceManager.getStringValue(SharedPreferenceManager.USER_ACCOUNT_EMAIL));
        binding.txtPhone.setText(preferenceManager.getStringValue(SharedPreferenceManager.USER_PROFILE_PHONE_KEY));
    }
}