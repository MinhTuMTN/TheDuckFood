package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.response.StoreGetAllFoodsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FoodEndPoint {
    @GET("api/merchant/food")
    Call<StoreGetAllFoodsResponse> getAllFood(@Header("Authorization") String bearerToken,
                                              @Query("status") String status);
}
