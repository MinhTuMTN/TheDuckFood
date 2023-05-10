package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Store;

public class StoreLoginResponse {
    private boolean error;
    private String message;
    private Store store;
    private String authToken;

    public StoreLoginResponse(boolean error, String message, Store store, String authToken) {
        this.error = error;
        this.message = message;
        this.store = store;
        this.authToken = authToken;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
