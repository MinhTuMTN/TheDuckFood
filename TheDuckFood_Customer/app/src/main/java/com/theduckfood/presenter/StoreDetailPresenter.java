package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.StoreDetailResponse;
import com.theduckfood.presenter.contact.IStoreDetailView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailPresenter {
    IStoreDetailView iStoreDetailView;
    Context context;

    public StoreDetailPresenter(IStoreDetailView iStoreDetailView, Context context) {
        this.iStoreDetailView = iStoreDetailView;
        this.context = context;
    }

    public void getStoreDetail(Long storeId) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<StoreDetailResponse> call = userAccountEndpoint.getStoreDetail(
                storeId,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<StoreDetailResponse>() {
            @Override
            public void onResponse(Call<StoreDetailResponse> call, Response<StoreDetailResponse> response) {
                StoreDetailResponse storeDetailResponse = response.body();
                iStoreDetailView.getStoreDetail(storeDetailResponse);
            }

            @Override
            public void onFailure(Call<StoreDetailResponse> call, Throwable t) {

            }
        });
    }

    public void favorite(Long storeId) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.favorite(
                storeId,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                iStoreDetailView.favorite(simpleMessageResponse);
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {

            }
        });
    }
}
