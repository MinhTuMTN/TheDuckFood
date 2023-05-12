package com.theduckfood.merchant.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.activities.ChangePasswordActivity;
import com.theduckfood.merchant.activities.LoginActivity;
import com.theduckfood.merchant.activities.ProfileActivity;
import com.theduckfood.merchant.databinding.FragmentAccountBinding;
import com.theduckfood.merchant.databinding.PopupLogoutConfirmBinding;
import com.theduckfood.merchant.model.Store;
import com.theduckfood.merchant.model.response.GetStoreProfileResponse;
import com.theduckfood.merchant.presenter.HomePresenter;
import com.theduckfood.merchant.presenter.contact.IHomeView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import java.text.DecimalFormat;

public class AccountFragment extends Fragment implements IHomeView {
    private static AccountFragment instance;
    private FragmentAccountBinding binding;
    HomePresenter homePresenter;
    Store store;

    public static AccountFragment newInstance() {
        return instance == null ? new AccountFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenter(this, getContext());
        homePresenter.loadProfile();

        addListener();
    }

    private void addListener() {
        binding.constraintHoSoCaNhan.setOnClickListener(this::switchToProfileActivity);
        binding.constraintMatKhau.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
            startActivity(intent);
        });
        binding.constraintDangXuat.setOnClickListener(this::logOut);
        binding.constraintLayout.setOnClickListener(this::switchToProfileActivity);
        binding.constraintGoi.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+900));//change the number.
            startActivity(callIntent);
        });
    }

    private void switchToProfileActivity(View view) {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra("store", store);
        startActivity(intent);
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

    @Override
    public void loadProfile(GetStoreProfileResponse storeProfileResponse) {
        if (storeProfileResponse == null || storeProfileResponse.getStore() == null) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }
        Store store = storeProfileResponse.getStore();
        this.store = store;

        binding.txtTen.setText(store.getStoreName());
        binding.ratingBar.setRating(store.getRate());

        DecimalFormat df = new DecimalFormat("#.#");
        String formatted = df.format(store.getRate()) + "/5.0";
        binding.txtNumStar.setText(formatted);
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(store.getAvatar())
                    .into(binding.imgAva);
        }
    }

    @Override
    public void isLoading(boolean isLoading) {

    }
}