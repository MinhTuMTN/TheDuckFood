package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.OrderEndpoint;
import com.theduckfood.merchant.model.response.StoreGetStatisticResponse;
import com.theduckfood.merchant.presenter.contact.IWalletView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletPresenter {
    IWalletView iWalletView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public WalletPresenter(IWalletView iWalletView, Context context) {
        this.iWalletView = iWalletView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getStatistic() {
        OrderEndpoint orderEndpoint = APIUtil.getRetrofit().create(OrderEndpoint.class);
        Call<StoreGetStatisticResponse> call = orderEndpoint.getStatistic(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<StoreGetStatisticResponse>() {
            @Override
            public void onResponse(Call<StoreGetStatisticResponse> call, Response<StoreGetStatisticResponse> response) {
                iWalletView.getStatisticResponse(response.body());
            }

            @Override
            public void onFailure(Call<StoreGetStatisticResponse> call, Throwable t) {
                Log.e("WalletActivity", t.getMessage());
                iWalletView.getStatisticResponse(null);
            }
        });
    }
}
