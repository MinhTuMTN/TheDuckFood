package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.model.response.StoreGetOrdersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface OrderEndpoint {
    @GET("/api/merchant/order")
    Call<StoreGetOrdersResponse> getOrders(
            @Header("Authorization") String authToken,
            @Query("status") String status);
}
