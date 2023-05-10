package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.theduckfood.databinding.ActivityUpdateProfileBinding;
import com.theduckfood.model.request.UpdateProfileRequest;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.UpdateProfilePresenter;
import com.theduckfood.presenter.contact.IUpdateProfileView;

public class UpdateProfileActivity extends AppCompatActivity implements IUpdateProfileView {
    ActivityUpdateProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(this, UpdateProfileActivity.this);

        loadUserProfile(updateProfilePresenter);
        addEvents(updateProfilePresenter);
    }

    private void loadUserProfile(UpdateProfilePresenter updateProfilePresenter) {
        updateProfilePresenter.getProfile();
    }

    private void addEvents(UpdateProfilePresenter updateProfilePresenter) {
        binding.btnUpdateProfile.setOnClickListener(v -> btnUpdateProfileClick(updateProfilePresenter));
        binding.btnBack.setOnClickListener(v -> switchToMainActivity());
    }

    private void btnUpdateProfileClick(UpdateProfilePresenter updateProfilePresenter) {
        String fullName = binding.edtFullName.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();

        if (fullName.isEmpty()) {
            binding.edtFullName.setError("Vui lòng nhập họ tên đầy đủ");
            binding.edtFullName.requestFocus();
            return;
        } else if (phone.isEmpty()) {
            binding.edtPhone.setError("Vui lòng nhập số điện thoại");
            binding.edtPhone.requestFocus();
            return;
        }
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(fullName, phone);
        updateProfilePresenter.updateProfile(updateProfileRequest);
    }

    private void switchToMainActivity() {
        Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void updateProfile(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
        switchToMainActivity();
    }

    @Override
    public void getProfile(GetProfileResponse getProfileResponse) {
        if (getProfileResponse == null) {
            Toast.makeText(this, "Lỗi! Không thể lấy thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.edtFullName.setText(getProfileResponse.getUserProfile().getFullName());
        binding.edtPhone.setText(getProfileResponse.getUserProfile().getPhone());
    }
}
