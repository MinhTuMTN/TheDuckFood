package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;


import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.StoreAccountEndpoint;
import com.theduckfood.merchant.model.Store;
import com.theduckfood.merchant.model.request.StoreLoginRequest;
import com.theduckfood.merchant.model.response.StoreLoginResponse;
import com.theduckfood.merchant.presenter.contact.ILoginView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private ILoginView iLoginView;
    private Context context;

    public LoginPresenter(ILoginView iLoginView, Context context) {
        this.iLoginView = iLoginView;
        this.context = context;
    }

    public void login(String email, String password) {
        iLoginView.isLogging(true);
        StoreAccountEndpoint storeAccountEndpoint = APIUtil.getRetrofit().create(StoreAccountEndpoint.class);
        Call<StoreLoginResponse> call = storeAccountEndpoint.storeLogin(new StoreLoginRequest(email, password));
        call.enqueue(new Callback<StoreLoginResponse>() {
            @Override
            public void onResponse(Call<StoreLoginResponse> call, Response<StoreLoginResponse> response) {
                StoreLoginResponse loginResponse = response.body();
                iLoginView.login(loginResponse);

                if (loginResponse != null) {
                    Store userProfile = loginResponse.getStore();
                    SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
                    sharedPreferenceManager.saveLoginInfo(loginResponse.getAuthToken());
                }
                iLoginView.isLogging(false);

            }

            @Override
            public void onFailure(Call<StoreLoginResponse> call, Throwable t) {
                Log.e("LoginActivity", t.getMessage());
                iLoginView.login(null);
                iLoginView.isLogging(false);
            }
        });
    }
}
