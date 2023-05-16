package com.theduckfood.theduckfoodpartner.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.theduckfoodpartner.api.APIUtil;
import com.theduckfood.theduckfoodpartner.api.DeliveryEndpoint;
import com.theduckfood.theduckfoodpartner.model.response.DeliveryGetOrdersResponse;
import com.theduckfood.theduckfoodpartner.presenter.contact.IOrderView;
import com.theduckfood.theduckfoodpartner.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {
    IOrderView iOrderView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public OrderPresenter(IOrderView iOrderView, Context context) {
        this.iOrderView = iOrderView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getOrders(String type) {
        iOrderView.loading(true);
        DeliveryEndpoint deliveryEndpoint = APIUtil.getRetrofit().create(DeliveryEndpoint.class);
        Call<DeliveryGetOrdersResponse> call = deliveryEndpoint.getOrders(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                type
        );
        call.enqueue(new Callback<DeliveryGetOrdersResponse>() {
            @Override
            public void onResponse(Call<DeliveryGetOrdersResponse> call, Response<DeliveryGetOrdersResponse> response) {
                iOrderView.getOrdersResponse(response.body());
            }

            @Override
            public void onFailure(Call<DeliveryGetOrdersResponse> call, Throwable t) {
                Log.e("GetOrders", t.getMessage());
                iOrderView.getOrdersResponse(null);
            }
        });
    }
}
