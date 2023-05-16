package com.theduckfood.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.theduckfood.activities.ChangePasswordActivity;
import com.theduckfood.activities.LoginActivity;
import com.theduckfood.activities.MainActivity;
import com.theduckfood.activities.StoreDetailActivity;
import com.theduckfood.activities.UpdateProfileActivity;
import com.theduckfood.databinding.FragmentProfileBinding;
import com.theduckfood.databinding.PopupLogoutConfirmBinding;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.presenter.GetProfilePresenter;
import com.theduckfood.presenter.contact.IProfileView;
import com.theduckfood.util.SharedPreferenceManager;

public class ProfileFragment extends Fragment implements IProfileView {
    FragmentProfileBinding binding;

    Dialog dialog;
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
        binding.cardChangePassword.setOnClickListener(this::changePassword);
        binding.btnEditProfile.setOnClickListener(this::updateProfile);
        binding.btnBack3.setOnClickListener(this::switchToMainActivity);
    }


    private void updateProfile(View view) {
        Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
        startActivity(intent);
    }

    private void logOut(View view) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        PopupLogoutConfirmBinding popupLogoutConfirmBinding = PopupLogoutConfirmBinding.inflate(LayoutInflater.from(getActivity()));
        dialog.setContentView(popupLogoutConfirmBinding.getRoot());

        popupLogoutConfirmBinding.btnClose.setOnClickListener(v -> dialog.dismiss());
        popupLogoutConfirmBinding.btnLogout.setOnClickListener(v -> {
            new SharedPreferenceManager(getActivity()).clear();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            requireActivity().startActivity(intent);
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void changePassword(View view){
        Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
        startActivity(intent);
    }
    private void loadUserData(View view) {
        GetProfilePresenter getProfilePresenter = new GetProfilePresenter(this, getActivity());
        getProfilePresenter.getProfile();
    }

    @Override
    public void getProfile(GetProfileResponse getProfileResponse) {
        if (getProfileResponse == null) {
            Toast.makeText(getContext(), "Lỗi! Không thể lấy thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getProfileResponse.isError()) {
            Toast.makeText(getContext(), getProfileResponse.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getContext());
        sharedPreferenceManager.setStringValue(
                SharedPreferenceManager.USER_PROFILE_FULL_NAME_KEY,
                getProfileResponse.getUserProfile().getFullName()
        );
        binding.txtFullName.setText(getProfileResponse.getUserProfile().getFullName());
        binding.txtEmail.setText(getProfileResponse.getUserAccount().getEmail());
        binding.txtPhone.setText(getProfileResponse.getUserProfile().getPhone());
    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}