package com.theduckfood.merchant.model.response;


import java.util.List;

public class StoreGetOrdersResponse {
    private boolean error;
    private String message;
    private String storeName;
    private List<StoreOrderResponse> orders;

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<StoreOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<StoreOrderResponse> orders) {
        this.orders = orders;
    }
}
