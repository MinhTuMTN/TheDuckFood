package com.theduckfood.merchant.api;

import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.model.response.StoreGetAllFoodsResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface FoodEndPoint {
    @GET("api/merchant/food")
    Call<StoreGetAllFoodsResponse> getAllFood(@Header("Authorization") String bearerToken,
                                              @Query("status") String status);
    @GET("api/merchant/food/update-status")
    Call<SimpleMessageResponse> updateFoodStatus(@Header("Authorization") String bearerToken,
                                                 @Query("foodId") Long foodId,
                                                 @Query("status") String status);
    @Multipart
    @POST("api/merchant/food/add-food")
    Call<SimpleMessageResponse> addFood(
            @Header("Authorization") String bearerToken,
            @Part("foodName") RequestBody foodName,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part MultipartBody.Part image
    );
}
