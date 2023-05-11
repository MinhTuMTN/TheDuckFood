package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.StoreAccountEndpoint;
import com.theduckfood.merchant.model.request.StoreUpdateInfoRequest;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.contact.IProfileView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    IProfileView iProfileView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public ProfilePresenter(IProfileView iProfileView, Context context) {
        this.iProfileView = iProfileView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }


    public void updateProfile(StoreUpdateInfoRequest storeUpdateInfoRequest) {
        StoreAccountEndpoint storeAccountEndpoint = APIUtil.getRetrofit().create(StoreAccountEndpoint.class);
        Call<SimpleMessageResponse> call = storeAccountEndpoint.updateMerchantProfile(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                storeUpdateInfoRequest);
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                iProfileView.updateProfile(response.body());
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("ProfileActivity", t.getMessage());
                iProfileView.updateProfile(null);
            }
        });
    }
}
