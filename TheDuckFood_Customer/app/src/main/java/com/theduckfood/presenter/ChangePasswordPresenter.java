package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.contact.IChangePasswordView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {
    private IChangePasswordView iChangePasswordView;
    private Context context;

    public ChangePasswordPresenter(IChangePasswordView iChangePasswordView, Context context) {
        this.iChangePasswordView = iChangePasswordView;
        this.context = context;
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest){
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.changePassword(
                changePasswordRequest,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                iChangePasswordView.changePassword(simpleMessageResponse);
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {

            }
        });
    }
}
