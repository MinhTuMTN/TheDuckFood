package com.theduckfood.theduckfoodpartner.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SharedPreferenceManager {
    public static final String THE_DUCK_FOOD_REFERENCE_NAME = "TheDuckFood";
    public static final String USER_PROFILE_FULL_NAME_KEY = "fullName";
    public static final String USER_PROFILE_PHONE_KEY = "phone";
    public static final String USER_ACCOUNT_EMAIL = "email";
    public static final String USER_PROFILE_POINTS_KEY = "points";
    public static final String USER_PROFILE_BALANCE_KEY = "balance";
    public static final String USER_PROFILE_FCM_TOKEN_KEY = "fcmToken";
    public static final String AUTH_TOKEN_KEY = "authToken";
    public static final String KEY_CART_ITEMS = "cart_items";
    public static final String KEY_CART_SHOP_ID = "shop_id";

    private Context context;

    public SharedPreferenceManager(Context context) {
        this.context = context;
    }

    public void saveLoginInfo (String deliveryName, String authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PROFILE_FULL_NAME_KEY, deliveryName);
        editor.putString(AUTH_TOKEN_KEY, authToken);
        editor.apply();
    }


    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public void setStringValue(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setLongValue(String key, Long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public float getFloatValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0f);
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public Long getLongValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THE_DUCK_FOOD_REFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0L);
    }
}
