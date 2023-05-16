package com.theduckfood.theduckfoodpartner.presenter;

import android.content.Context;

import com.theduckfood.theduckfoodpartner.api.APIUtil;
import com.theduckfood.theduckfoodpartner.api.DeliveryEndpoint;
import com.theduckfood.theduckfoodpartner.model.response.SimpleMessageResponse;
import com.theduckfood.theduckfoodpartner.presenter.contact.IMainView;

import retrofit2.Call;

public class MainPresenter {
    IMainView iMainView;
    Context context;

    public MainPresenter(IMainView iMainView, Context context) {
        this.iMainView = iMainView;
        this.context = context;
    }

    public void updateOrderStatus(Long orderId, String status) {
        DeliveryEndpoint deliveryEndpoint = APIUtil.getRetrofit().create(DeliveryEndpoint.class);
        Call<SimpleMessageResponse> call = deliveryEndpoint.getDeliveryStatus(orderId, status);
    }
}
