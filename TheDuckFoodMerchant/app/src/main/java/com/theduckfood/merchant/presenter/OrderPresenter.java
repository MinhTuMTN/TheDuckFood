package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.OrderEndpoint;
import com.theduckfood.merchant.model.response.StoreGetOrdersResponse;
import com.theduckfood.merchant.presenter.contact.IOrderView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {
    private IOrderView iOrderView;
    private Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public OrderPresenter(IOrderView iOrderView, Context context) {
        this.iOrderView = iOrderView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getOrders(String status) {
        OrderEndpoint orderEndpoint = APIUtil.getRetrofit().create(OrderEndpoint.class);
        Call<StoreGetOrdersResponse> call = orderEndpoint.getOrders(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                status
        );
        call.enqueue(new Callback<StoreGetOrdersResponse>() {
            @Override
            public void onResponse(Call<StoreGetOrdersResponse> call, Response<StoreGetOrdersResponse> response) {
                iOrderView.getOrdersResponse(response.body());
            }

            @Override
            public void onFailure(Call<StoreGetOrdersResponse> call, Throwable t) {
                Log.e("OrdersFragment", t.getMessage());
                Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
