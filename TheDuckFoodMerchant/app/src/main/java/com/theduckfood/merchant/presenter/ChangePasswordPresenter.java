package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.StoreAccountEndpoint;
import com.theduckfood.merchant.model.request.ChangePasswordRequest;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.contact.IChangePasswordView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {
    IChangePasswordView iChangePasswordView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public ChangePasswordPresenter(IChangePasswordView iChangePasswordView, Context context) {
        this.iChangePasswordView = iChangePasswordView;
        this.context = context;
        sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void changePassword(ChangePasswordRequest request) {
        StoreAccountEndpoint storeAccountEndpoint = APIUtil.getRetrofit().create(StoreAccountEndpoint.class);
        Call<SimpleMessageResponse> call = storeAccountEndpoint.changePassword(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                request
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                iChangePasswordView.changePasswordResponse(response.code());
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("ChangePasswordActivity", t.getMessage());
                iChangePasswordView.changePasswordResponse(null);
            }
        });
    }
}
