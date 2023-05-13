package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.CouponEndpoint;
import com.theduckfood.merchant.model.response.StoreGetCouponsResponse;
import com.theduckfood.merchant.presenter.contact.ICouponView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponPresenter {
    ICouponView iCouponView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public CouponPresenter(ICouponView iCouponView, Context context) {
        this.iCouponView = iCouponView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getCoupons(Boolean status) {
        CouponEndpoint couponEndpoint = APIUtil.getRetrofit().create(CouponEndpoint.class);
        Call<StoreGetCouponsResponse> call = couponEndpoint.getCoupons(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                status
        );
        call.enqueue(new Callback<StoreGetCouponsResponse>() {
            @Override
            public void onResponse(Call<StoreGetCouponsResponse> call, Response<StoreGetCouponsResponse> response) {
                iCouponView.getCouponsResponse(response.body());
            }

            @Override
            public void onFailure(Call<StoreGetCouponsResponse> call, Throwable t) {
                Log.e("CouponActivity", t.getMessage());
                iCouponView.getCouponsResponse(null);
            }
        });
    }
}
