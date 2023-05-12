package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.FoodEndPoint;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
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

    public void changeFoodStatus(Long foodId, String status) {
        FoodEndPoint foodEndPoint = APIUtil.getRetrofit().create(FoodEndPoint.class);
        Call<SimpleMessageResponse> call = foodEndPoint.updateFoodStatus(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                foodId,
                status);
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                if (simpleMessageResponse != null &&simpleMessageResponse.isError())
                    Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("MenuFragment", t.getMessage());
                Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
