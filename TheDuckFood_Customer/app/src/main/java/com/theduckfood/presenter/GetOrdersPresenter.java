package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.GetOrdersResponse;
import com.theduckfood.presenter.contact.IGetOrdersView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetOrdersPresenter {
    private Context context;
    private IGetOrdersView iGetOrdersView;

    public GetOrdersPresenter(Context context, IGetOrdersView iGetOrdersView) {
        this.context = context;
        this.iGetOrdersView = iGetOrdersView;
    }
    public void getAllOrders() {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<GetOrdersResponse> call = userAccountEndpoint.getAllOrders(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<GetOrdersResponse>() {
            @Override
            public void onResponse(Call<GetOrdersResponse> call, Response<GetOrdersResponse> response) {
                GetOrdersResponse getOrdersResponse = response.body();
                iGetOrdersView.getAllOrders(getOrdersResponse);
            }

            @Override
            public void onFailure(Call<GetOrdersResponse> call, Throwable t) {

            }
        });

    }
}
