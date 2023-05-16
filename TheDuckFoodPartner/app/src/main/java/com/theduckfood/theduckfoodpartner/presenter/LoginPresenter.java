package com.theduckfood.theduckfoodpartner.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.theduckfoodpartner.api.APIUtil;
import com.theduckfood.theduckfoodpartner.api.DeliveryEndpoint;
import com.theduckfood.theduckfoodpartner.model.request.LoginRequest;
import com.theduckfood.theduckfoodpartner.model.response.LoginResponse;
import com.theduckfood.theduckfoodpartner.presenter.contact.ILoginView;
import com.theduckfood.theduckfoodpartner.util.SharedPreferenceManager;

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
        DeliveryEndpoint deliveryEndpoint = APIUtil.getRetrofit().create(DeliveryEndpoint.class);
        Call<LoginResponse> call = deliveryEndpoint.login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                iLoginView.login(loginResponse);

                if (loginResponse != null && !loginResponse.isError()) {
                    SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
                    sharedPreferenceManager.saveLoginInfo(loginResponse.getDeliveryManName(), loginResponse.getAuthToken());
                }
                iLoginView.isLogging(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginActivity", t.getMessage());
                iLoginView.login(null);
                iLoginView.isLogging(false);
            }
        });
    }
}
