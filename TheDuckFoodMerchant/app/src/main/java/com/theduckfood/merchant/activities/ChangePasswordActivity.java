package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityChangePasswordBinding;
import com.theduckfood.merchant.model.request.ChangePasswordRequest;
import com.theduckfood.merchant.presenter.ChangePasswordPresenter;
import com.theduckfood.merchant.presenter.contact.IChangePasswordView;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity implements IChangePasswordView {
    ActivityChangePasswordBinding binding;
    ChangePasswordPresenter changePasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        changePasswordPresenter = new ChangePasswordPresenter(this, this);
        addEvent();
    }

    private void addEvent() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnDoiMatKhau.setOnClickListener(this::changePassword);
    }

    @Override
    public void changePasswordResponse(Integer statusCode) {
        String message;
        if (statusCode == null)
            message = "Đã có lỗi xảy ra. Vui lòng kiểm tra đường truyền";
        else if (statusCode == 401)
            message = "Mật khẩu không đúng";
        else if (statusCode == 200)
            message = "Đổi mật khẩu thành công";
        else
            message = "Mật khẩu không khớp";

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (statusCode != null && statusCode == 200)
            onBackPressed();
    }

    private void changePassword(View view) {
        String oldPassword = Objects.requireNonNull(binding.edtMatKhauHienTai.getText()).toString().trim();
        String newPassword = Objects.requireNonNull(binding.edtMatKhauMoi.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(binding.edtNhapLaiMatKhau.getText()).toString().trim();

        changePasswordPresenter.changePassword(new ChangePasswordRequest(
                oldPassword,
                newPassword,
                confirmPassword
        ));
    }
}