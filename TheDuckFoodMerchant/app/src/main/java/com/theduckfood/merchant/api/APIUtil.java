package com.theduckfood.merchant.api;

import com.theduckfood.merchant.util.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtil {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null)
            return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
