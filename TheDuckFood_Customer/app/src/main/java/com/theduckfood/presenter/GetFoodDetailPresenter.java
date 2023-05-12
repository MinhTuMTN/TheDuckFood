package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.FoodDetailResponse;
import com.theduckfood.presenter.contact.IGetFoodDetailView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFoodDetailPresenter {
    IGetFoodDetailView iGetFoodDetailView;
    Context context;

    public GetFoodDetailPresenter(IGetFoodDetailView iGetFoodDetailView, Context context) {
        this.iGetFoodDetailView = iGetFoodDetailView;
        this.context = context;
    }

    public void getFoodDetail (Long foodId) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<FoodDetailResponse> call = userAccountEndpoint.getFoodDetail(
                foodId,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<FoodDetailResponse>() {
            @Override
            public void onResponse(Call<FoodDetailResponse> call, Response<FoodDetailResponse> response) {
                FoodDetailResponse foodDetailResponse = response.body();
                iGetFoodDetailView.getFoodDetail(foodDetailResponse);
            }

            @Override
            public void onFailure(Call<FoodDetailResponse> call, Throwable t) {

            }
        });
    }
}
