package com.theduckfood.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.theduckfood.databinding.ActivityChangePasswordBinding;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.ChangePasswordPresenter;
import com.theduckfood.presenter.contact.IChangePasswordView;

public class ChangePasswordActivity extends AppCompatActivity implements IChangePasswordView {
    ActivityChangePasswordBinding binding;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEvents();
    }

    private void addEvents() {
        binding.btnChangePass.setOnClickListener(v -> btnChangePassClick());
        binding.btnBack3.setOnClickListener(v -> switchToMainActivity());
    }

    private void btnChangePassClick() {
        String oldPass = binding.edtOldPassword.getText().toString().trim();
        String newPass = binding.edtNewPassword.getText().toString().trim();
        String confirmPass = binding.edtConfirmPassword.getText().toString().trim();

        if (oldPass.isEmpty()) {
            binding.edtOldPassword.setError("Vui lòng nhập mật khẩu cũ");
            binding.edtOldPassword.requestFocus();
            return;
        } else if (newPass.isEmpty()) {
            binding.edtNewPassword.setError("Vui lòng nhập mật khẩu mới");
            binding.edtNewPassword.requestFocus();
            return;
        } else if (confirmPass.isEmpty()) {
            binding.edtConfirmPassword.setError("Vui lòng xác nhận lại mật khẩu");
            binding.edtConfirmPassword.requestFocus();
            return;
        }

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(oldPass, newPass, confirmPass);

        ChangePasswordPresenter changePasswordPresenter = new ChangePasswordPresenter(this, ChangePasswordActivity.this);
        changePasswordPresenter.changePassword(changePasswordRequest);
    }

    @Override
    public void changePassword(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (simpleMessageResponse.isError()) {
            Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
        switchToMainActivity();
    }

    public void switchToMainActivity() {
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
