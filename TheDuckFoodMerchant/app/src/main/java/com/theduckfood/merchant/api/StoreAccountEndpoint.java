package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.request.StoreLoginRequest;
import com.theduckfood.merchant.model.response.StoreLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StoreAccountEndpoint {
    @POST("/api/merchant/login")
    Call<StoreLoginResponse> storeLogin(@Body StoreLoginRequest loginRequest);
}
