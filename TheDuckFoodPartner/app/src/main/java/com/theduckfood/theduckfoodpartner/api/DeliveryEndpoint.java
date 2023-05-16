package com.theduckfood.theduckfoodpartner.api;


import com.theduckfood.theduckfoodpartner.model.response.SimpleMessageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeliveryEndpoint {

    @GET("/api/delivery")
    Call<SimpleMessageResponse> getDeliveryStatus(@Query("orderId") Long orderId, @Query("status") String status);

}
