package com.theduckfood.api;

import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.respone.LoginResponse;
import com.theduckfood.model.respone.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAccountEndpoint {
    @POST("/api/users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/users/register")
    Call<SignUpResponse> register(@Body SignUpRequest signUpRequest);
}
