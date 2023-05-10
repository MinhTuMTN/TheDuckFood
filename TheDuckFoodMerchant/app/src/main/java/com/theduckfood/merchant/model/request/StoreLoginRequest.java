package com.theduckfood.merchant.model.request;

public class StoreLoginRequest {
    private String email;
    private String password;

    public StoreLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
