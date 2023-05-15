package com.theduckfood.api;

import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.CreateOrderRequest;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.request.UpdateProfileRequest;
import com.theduckfood.model.request.UserReviewRequest;
import com.theduckfood.model.respone.CreateOrderResponse;
import com.theduckfood.model.respone.FoodDetailResponse;
import com.theduckfood.model.respone.GetOrdersResponse;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.model.respone.SearchResponse;
import com.theduckfood.model.respone.SignUpResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.StoreDetailResponse;
import com.theduckfood.model.respone.UserAddressResponse;
import com.theduckfood.model.respone.UserGetListStore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserAccountEndpoint {
    @POST("/api/users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/users/register")
    Call<SignUpResponse> register(@Body SignUpRequest signUpRequest);

    @FormUrlEncoded
    @POST("/api/users/update-fcm-token")
    Call<SimpleMessageResponse> updateFCMToken(@Field("fcmToken") String fcmToken, @Header("Authorization") String authToken);

    @GET("/api/users/profile")
    Call<GetProfileResponse> getProfile(@Header("Authorization") String authToken);

    @PUT("/api/users/update-profile")
    Call<SimpleMessageResponse> updateProfile(@Body UpdateProfileRequest updateProfileRequest, @Header("Authorization") String authToken);

    @POST("/api/users/change-password")
    Call<SimpleMessageResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest, @Header("Authorization") String authToken);

    @GET("/api/store")
    Call<StoreDetailResponse> getStoreDetail(@Query("storeId") Long storeId, @Header("Authorization") String authToken);

    @GET("/api/store/favorites")
    Call<SimpleMessageResponse> favorite(@Query("storeId") Long storeId, @Header("Authorization") String authToken);

    @GET("/api/foods")
    Call<FoodDetailResponse> getFoodDetail(@Query("foodId") Long foodId, @Header("Authorization") String authToken);

    @GET("/api/orders")
    Call<GetOrdersResponse> getAllOrders(@Header("Authorization") String authToken);

    @POST("/api/store/review")
    Call<SimpleMessageResponse> review(@Body UserReviewRequest userReviewRequest, @Header("Authorization") String authToken);

    @POST("/api/orders/create")
    Call<CreateOrderResponse> createOrder(@Body CreateOrderRequest createOrderRequest, @Header("Authorization") String authToken);

    @GET("/api/users/address")
    Call<UserAddressResponse> getUserAddress(@Header("Authorization") String authToken);

    @POST("/api/users/add-address")
    Call<SimpleMessageResponse> addUserAddress(@Query("streetAddress") String streetAddress, @Header("Authorization") String authToken);

    @DELETE("/api/users/delete-address")
    Call<UserAddressResponse> deleteUserAddress(@Query("userAddressId") Long userAddressId, @Header("Authorization") String authToken);
    @GET("/api/store/list-store")
    Call<UserGetListStore> getListStore(@Header("Authorization") String authToken,
                                        @Query("page") int page,
                                        @Query("limit") int limit,
                                        @Query("sort") String sortParam,
                                        @Query("sortType") String sortType
    );

    @GET("/api/search")
    Call<SearchResponse> search(@Header("Authorization") String authToken,
                                @Query("page") int page,
                                @Query("limit") int limit,
                                @Query("q") String searchParam
    );
}
