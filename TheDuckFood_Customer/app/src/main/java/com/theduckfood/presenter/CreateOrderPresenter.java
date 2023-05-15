package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.request.CreateOrderRequest;
import com.theduckfood.model.respone.CreateOrderResponse;
import com.theduckfood.presenter.contact.ICreateOrderView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateOrderPresenter {
    private ICreateOrderView iCreateOrderView;
    private Context context;

    public CreateOrderPresenter(ICreateOrderView iCreateOrderView, Context context) {
        this.iCreateOrderView = iCreateOrderView;
        this.context = context;
    }
    public void createOrder(CreateOrderRequest createOrderRequest) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<CreateOrderResponse> call = userAccountEndpoint.createOrder(
                createOrderRequest,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                iCreateOrderView.createOrder(response.body());
            }

            @Override
            public void onFailure(Call<CreateOrderResponse> call, Throwable t) {

            }
        });
    }


}
