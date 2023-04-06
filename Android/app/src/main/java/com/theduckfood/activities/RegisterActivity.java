package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import com.theduckfood.R;
import com.theduckfood.databinding.ActivityRegisterBinding;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.respone.SignUpResponse;
import com.theduckfood.presenter.RegisterPresenter;
import com.theduckfood.presenter.contact.IRegisterView;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    ActivityRegisterBinding binding;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.txtLogin.setOnClickListener(v -> onBackPressed());
        binding.btnSignUp.setOnClickListener(v -> signUpProcess());
    }

    private void signUpProcess() {
        String fullName = binding.edtFullName.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        String confirmPassword = binding.edtConfirmPassword.getText().toString().trim();

        RegisterPresenter registerPresenter = new RegisterPresenter(this, this);
        registerPresenter.register(new SignUpRequest(fullName, phone, email, password));
    }

    @Override
    public void register(SignUpResponse signUpResponse) {
        if (signUpResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        Toast.makeText(this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
        switchToMainActivity();
    }

    @Override
    public void loading(boolean isLoading) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        if (isLoading)
            dialog.show();
        else
            dialog.dismiss();
    }

    private void switchToMainActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}