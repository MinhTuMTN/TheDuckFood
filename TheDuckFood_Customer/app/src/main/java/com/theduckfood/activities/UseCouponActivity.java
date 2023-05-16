package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.theduckfood.adapter.UseCouponAdapter;
import com.theduckfood.databinding.ActivityUseCouponBinding;
import com.theduckfood.model.Coupon;
import com.theduckfood.model.respone.GetCouponsResponse;
import com.theduckfood.presenter.GetCouponsPresenter;
import com.theduckfood.presenter.contact.IGetCouponsView;

import java.util.List;

public class UseCouponActivity extends AppCompatActivity implements IGetCouponsView {
    ActivityUseCouponBinding binding;

    GetCouponsPresenter getCouponsPresenter;

    UseCouponAdapter useCouponAdapter;

    Long storeId;

    List<Coupon> coupons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUseCouponBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentData();
        getCouponsPresenter = new GetCouponsPresenter(this, this);
        getCouponsPresenter.getCoupons(storeId);
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {
        storeId = getIntent().getLongExtra("storeId",18L);
    }

    private void loadData() {
        useCouponAdapter = new UseCouponAdapter(this, coupons);
        binding.recyclerCoupon.setAdapter(useCouponAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerCoupon.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getCoupons(GetCouponsResponse getCouponsResponse) {
        if (getCouponsResponse == null ) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            binding.recyclerCoupon.setVisibility(View.GONE);
            binding.constraintKhongTimThay.setVisibility(View.VISIBLE);
            return;
        }

        if (getCouponsResponse.getCoupons().isEmpty()) {
            binding.recyclerCoupon.setVisibility(View.GONE);
            binding.constraintKhongTimThay.setVisibility(View.VISIBLE);
            return;
        }

        binding.recyclerCoupon.setVisibility(View.VISIBLE);
        binding.constraintKhongTimThay.setVisibility(View.GONE);

        coupons = getCouponsResponse.getCoupons();
        loadData();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}