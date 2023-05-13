package com.theduckfood.merchant.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.theduckfood.merchant.util.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtil {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        if (retrofit == null)
            return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
