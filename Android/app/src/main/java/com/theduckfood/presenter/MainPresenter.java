package com.theduckfood.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.contact.IMainView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private IMainView iMainView;
    private Context context;

    public MainPresenter(IMainView iMainView, Context context) {
        this.iMainView = iMainView;
        this.context = context;
    }

    public void updateFCMToken(String fcmToken) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.updateFCMToken(fcmToken,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();

                if (simpleMessageResponse == null || !simpleMessageResponse.isError()) {
                    sharedPreferenceManager.setStringValue(SharedPreferenceManager.USER_PROFILE_FCM_TOKEN_KEY, fcmToken);
                }
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("LoginActivityUpdateFCMToken", t.getMessage());
            }
        });
    }
}
