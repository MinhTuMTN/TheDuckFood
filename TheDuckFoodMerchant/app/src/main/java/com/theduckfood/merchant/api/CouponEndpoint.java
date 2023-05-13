package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.request.AddCouponRequest;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.model.response.StoreGetCouponsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CouponEndpoint {
    @GET("/api/merchant/coupon")
    Call<StoreGetCouponsResponse> getCoupons(
            @Header("Authorization") String authToken,
            @Query("status") boolean status);
    @POST("/api/merchant/coupon")
    Call<SimpleMessageResponse> addCoupon(
            @Header("Authorization") String authToken,
            @Body AddCouponRequest request);
}
