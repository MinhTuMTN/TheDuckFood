package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.UserAddressResponse;
import com.theduckfood.presenter.contact.IUserAddressView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAddressPresenter {
    private IUserAddressView iUserAddressView;
    private Context context;

    public UserAddressPresenter(IUserAddressView iUserAddressView, Context context) {
        this.iUserAddressView = iUserAddressView;
        this.context = context;
    }
    public void getUserAddress() {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<UserAddressResponse> call = userAccountEndpoint.getUserAddress(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<UserAddressResponse>() {
            @Override
            public void onResponse(Call<UserAddressResponse> call, Response<UserAddressResponse> response) {
                iUserAddressView.getUserAddress(response.body());
            }

            @Override
            public void onFailure(Call<UserAddressResponse> call, Throwable t) {

            }
        });
    }

    public void addUserAddress(String streetAddress) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.addUserAddress(
                streetAddress,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                iUserAddressView.addUserAddress(response.body());
                getUserAddress();
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {

            }
        });
    }

    public void deleteUserAddress(Long userAddressId) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<UserAddressResponse> call = userAccountEndpoint.deleteUserAddress(
                userAddressId,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<UserAddressResponse>() {
            @Override
            public void onResponse(Call<UserAddressResponse> call, Response<UserAddressResponse> response) {
                iUserAddressView.deleteUserAddress(response.body());
            }

            @Override
            public void onFailure(Call<UserAddressResponse> call, Throwable t) {

            }
        });
    }
}
