package com.theduckfood.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.UserAccount;
import com.theduckfood.model.UserProfile;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.model.respone.SignUpResponse;
import com.theduckfood.presenter.contact.IRegisterView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    IRegisterView iRegisterView;
    Context context;

    public RegisterPresenter(IRegisterView iRegisterView, Context context) {
        this.iRegisterView = iRegisterView;
        this.context = context;
    }

    public void register(SignUpRequest signUpRequest) {
        iRegisterView.loading(true);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SignUpResponse> call = userAccountEndpoint.register(signUpRequest);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                iRegisterView.register(signUpResponse);
                iRegisterView.loading(false);

                if (signUpResponse != null) {
                    UserProfile userProfile = signUpResponse.getUserProfile();
                    UserAccount userAccount = signUpResponse.getUserAccount();
                    if (userAccount == null || userProfile == null)
                        return;

                    SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
                    sharedPreferenceManager.saveLoginInfo(userProfile, userAccount.getEmail(), signUpResponse.getAuthToken());
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.e("LoginActivity", t.getMessage());
                iRegisterView.register(null);
                iRegisterView.loading(false);
            }
        });
    }
}
