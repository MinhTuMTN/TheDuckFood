package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.FoodEndPoint;
import com.theduckfood.merchant.model.response.StoreGetAllFoodsResponse;
import com.theduckfood.merchant.presenter.contact.IMenuView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPresenter {
    IMenuView iMenuView;
    Context context;
    final SharedPreferenceManager sharedPreferenceManager;

    public MenuPresenter(IMenuView iMenuView, Context context) {
        this.iMenuView = iMenuView;
        this.context = context;
        sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getListFoods(String status) {
        iMenuView.loading(true);
        FoodEndPoint foodEndPoint = APIUtil.getRetrofit().create(FoodEndPoint.class);
        Call<StoreGetAllFoodsResponse> call = foodEndPoint.getAllFood(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                status);
        call.enqueue(new Callback<StoreGetAllFoodsResponse>() {
            @Override
            public void onResponse(Call<StoreGetAllFoodsResponse> call, Response<StoreGetAllFoodsResponse> response) {
                StoreGetAllFoodsResponse foodsResponse = response.body();
                iMenuView.loadFoods(foodsResponse);
                iMenuView.loading(false);
            }

            @Override
            public void onFailure(Call<StoreGetAllFoodsResponse> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
                iMenuView.loadFoods(null);
                iMenuView.loading(false);
            }
        });
    }
}
