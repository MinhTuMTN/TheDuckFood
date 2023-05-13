package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.adapter.CouponAdapter;
import com.theduckfood.merchant.databinding.ActivityCouponsBinding;
import com.theduckfood.merchant.model.response.StoreGetCouponsResponse;
import com.theduckfood.merchant.presenter.CouponPresenter;
import com.theduckfood.merchant.presenter.contact.ICouponView;

public class CouponsActivity extends AppCompatActivity implements ICouponView {
    ActivityCouponsBinding binding;
    CouponPresenter couponPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCouponsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        couponPresenter = new CouponPresenter(this, this);
        addEvent();
        couponPresenter.getCoupons(true);
    }

    private void addEvent() {
        binding.btnCouponDangHoatDong.setOnClickListener(v -> {
            couponPresenter.getCoupons(true);
            switchCoupons(true);
        });
        binding.btnCouponDaNgungHoatDong.setOnClickListener(v -> {
            couponPresenter.getCoupons(false);
            switchCoupons(false);
        });
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnTaoMaCoupon.setOnClickListener(v -> {
            startActivity(new Intent(this, AddCouponActivity.class));
        });
    }

    private void switchCoupons(boolean available) {
        binding.txtDangChay.setTextColor(ContextCompat.getColor(this, R.color.black));
        binding.txtHoanTat.setTextColor(ContextCompat.getColor(this, R.color.black));

        if (available) {
            binding.txtDangChay.setTextColor(ContextCompat.getColor(this, R.color.coral));
        } else {
            binding.txtHoanTat.setTextColor(ContextCompat.getColor(this, R.color.coral));
        }
    }

    @Override
    public void getCouponsResponse(StoreGetCouponsResponse couponsResponse) {
        if (couponsResponse == null || couponsResponse.isError()) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            binding.notFound.setVisibility(View.VISIBLE);
            binding.listCoupon.setVisibility(View.GONE);
            return;
        }

        if (couponsResponse.getCoupons().size() == 0) {
            binding.notFound.setVisibility(View.VISIBLE);
            binding.listCoupon.setVisibility(View.GONE);
            return;
        }

        binding.listCoupon.setVisibility(View.VISIBLE);
        binding.notFound.setVisibility(View.GONE);

        CouponAdapter couponAdapter = new CouponAdapter(this, couponsResponse.getCoupons());
        binding.listCoupon.setAdapter(couponAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.listCoupon.setLayoutManager(linearLayoutManager);
    }
}