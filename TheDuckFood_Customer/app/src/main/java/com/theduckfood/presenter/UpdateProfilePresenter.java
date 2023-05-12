package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.request.UpdateProfileRequest;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.contact.IUpdateProfileView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePresenter {
    private IUpdateProfileView iUpdateProfileView;
    private Context context;

    public UpdateProfilePresenter(IUpdateProfileView iUpdateProfileView, Context context) {
        this.iUpdateProfileView = iUpdateProfileView;
        this.context = context;
    }

    public void getProfile() {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<GetProfileResponse> call = userAccountEndpoint.getProfile(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                GetProfileResponse getProfileResponse = response.body();
                iUpdateProfileView.getProfile(getProfileResponse);
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });
    }

    public void updateProfile(UpdateProfileRequest updateProfileRequest) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.updateProfile(
                updateProfileRequest,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                iUpdateProfileView.updateProfile(simpleMessageResponse);
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {

            }
        });
    }
}
