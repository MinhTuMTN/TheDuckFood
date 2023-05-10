package com.theduckfood.api;

import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.request.UpdateProfileRequest;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.model.respone.SignUpResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
}
