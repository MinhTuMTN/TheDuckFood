package com.theduckfood.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.UserProfile;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.contact.ILoginView;
import com.theduckfood.util.SharedPreferenceManager;

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
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<LoginResponse> call = userAccountEndpoint.login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                iLoginView.login(loginResponse);

                if (loginResponse != null) {
                    UserProfile userProfile = loginResponse.getUserProfile();
                    SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
                    sharedPreferenceManager.saveLoginInfo(userProfile, email, loginResponse.getAuthToken());
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
