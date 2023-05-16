package com.theduckfood.theduckfoodpartner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import com.theduckfood.theduckfoodpartner.R;
import com.theduckfood.theduckfoodpartner.databinding.ActivityLoginBinding;
import com.theduckfood.theduckfoodpartner.model.response.LoginResponse;
import com.theduckfood.theduckfoodpartner.presenter.LoginPresenter;
import com.theduckfood.theduckfoodpartner.presenter.contact.ILoginView;
import com.theduckfood.theduckfoodpartner.util.SharedPreferenceManager;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    ActivityLoginBinding binding;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        if (sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY) != null) {
            switchToMainActivity();
        }

        addEvents();
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(v -> btnLoginClick());
    }

    private void btnLoginClick() {
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            binding.edtEmail.setError("Vui lòng nhập email");
            binding.edtEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.setError("Email không hợp lệ");
            binding.edtEmail.requestFocus();
            return;
        } else if (password.isEmpty()) {
            binding.edtPassword.setError("Vui lòng nhập mật khẩu");
            binding.edtPassword.requestFocus();
            return;
        }
        LoginPresenter loginPresenter = new LoginPresenter(this, LoginActivity.this);
        loginPresenter.login(email, password);
    }

    private void switchToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void login(LoginResponse loginResponse) {
        if (loginResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        if (loginResponse.isError()) {
            Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
        switchToMainActivity();
    }

    @Override
    public void isLogging(boolean isLogging) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        if (isLogging)
            dialog.show();
        else
            dialog.dismiss();
    }
}