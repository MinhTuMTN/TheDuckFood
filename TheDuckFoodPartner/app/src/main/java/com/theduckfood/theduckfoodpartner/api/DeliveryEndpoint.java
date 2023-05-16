package com.theduckfood.theduckfoodpartner.api;


import com.theduckfood.theduckfoodpartner.model.request.LoginRequest;
import com.theduckfood.theduckfoodpartner.model.response.DeliveryGetOrdersResponse;
import com.theduckfood.theduckfoodpartner.model.response.LoginResponse;
import com.theduckfood.theduckfoodpartner.model.response.SimpleMessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeliveryEndpoint {
    @POST("/api/delivery/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/delivery")
    Call<SimpleMessageResponse> getDeliveryStatus(@Query("orderId") Long orderId, @Query("status") String status);

    @GET("/api/delivery/list-orders")
    Call<DeliveryGetOrdersResponse> getOrders(
            @Header("Authorization") String bearerToken,
            @Query("type") String type
    );

}
