package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.request.StoreLoginRequest;
import com.theduckfood.merchant.model.request.StoreUpdateInfoRequest;
import com.theduckfood.merchant.model.response.GetStoreProfileResponse;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.model.response.StoreLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StoreAccountEndpoint {
    @POST("/api/merchant/login")
    Call<StoreLoginResponse> storeLogin(@Body StoreLoginRequest loginRequest);

    @GET("/api/merchant/profile")
    Call<GetStoreProfileResponse> getStoreProfile(@Header("Authorization") String authToken);

    @GET("/api/merchant/change-status")
    Call<SimpleMessageResponse> changeStatus(
            @Header("Authorization") String authToken,
            @Query("status") boolean status);

    @POST("/api/merchant/update-profile")
    Call<SimpleMessageResponse> updateMerchantProfile(
            @Header("Authorization") String bearerToken,
            @Body StoreUpdateInfoRequest updateInfoRequest
    );
}
