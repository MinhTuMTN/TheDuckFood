package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.response.StoreGetAllReviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ReviewEndpoint {
    @GET("/api/merchant/review")
    Call<StoreGetAllReviewsResponse> getAllReviews(@Header("Authorization") String bearerToken);
}
