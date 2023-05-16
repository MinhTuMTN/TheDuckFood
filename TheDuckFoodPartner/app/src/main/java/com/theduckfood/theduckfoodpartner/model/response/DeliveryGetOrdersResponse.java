package com.theduckfood.theduckfoodpartner.model.response;


import java.util.List;

public class DeliveryGetOrdersResponse {
    private boolean error;
    private String message;
    private List<DeliveryOrderResponse> orders;

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

    public List<DeliveryOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<DeliveryOrderResponse> orders) {
        this.orders = orders;
    }
}
