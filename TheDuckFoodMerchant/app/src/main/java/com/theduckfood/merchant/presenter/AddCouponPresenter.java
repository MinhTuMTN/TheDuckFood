package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.CouponEndpoint;
import com.theduckfood.merchant.model.request.AddCouponRequest;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.contact.IAddCouponView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCouponPresenter {
    IAddCouponView iAddCouponView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public AddCouponPresenter(IAddCouponView iAddCouponView, Context context) {
        this.iAddCouponView = iAddCouponView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void addCoupon(AddCouponRequest addCouponRequest) {
        CouponEndpoint couponEndpoint = APIUtil.getRetrofit().create(CouponEndpoint.class);
        Call<SimpleMessageResponse> call = couponEndpoint.addCoupon(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                addCouponRequest
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                iAddCouponView.addCouponResponse(response.body());
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("AddCouponActivity", t.getMessage());
                iAddCouponView.addCouponResponse(null);
            }
        });
    }
}
