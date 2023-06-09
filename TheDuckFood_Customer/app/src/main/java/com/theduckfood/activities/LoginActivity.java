package com.theduckfood.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import com.theduckfood.R;
import com.theduckfood.databinding.ActivityLoginBinding;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.presenter.LoginPresenter;
import com.theduckfood.presenter.contact.ILoginView;
import com.theduckfood.util.Constant;
import com.theduckfood.util.SharedPreferenceManager;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    ActivityLoginBinding binding;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            createNotificationChannel();
        }

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        if (sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY) != null) {
            switchToMainActivity();
        }

        addEvents();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void createNotificationChannel() {
            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
            else {
                NotificationChannel channel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    channel = new NotificationChannel(
                            Constant.GENERAL_NOTIFICATION_CHANNEL_ID,
                            Constant.GENERAL_NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_HIGH);
                    getSystemService(NotificationManager.class).createNotificationChannel(channel);
                }
            }
    }

    private void addEvents() {
        binding.txtRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        binding.txtForgetPassword.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, PasswordRecoveryActivity.class)));
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

    private void switchToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}