package com.theduckfood.model.respone;

import com.theduckfood.model.Order;

import java.util.List;

public class GetOrdersResponse {
    private boolean error;
    private String message;
    private List<OrderResponse> orders;

    public GetOrdersResponse() {
    }

    public GetOrdersResponse(boolean error, String message, List<OrderResponse> orders) {
        this.error = error;
        this.message = message;
        this.orders = orders;
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

    public List<OrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
    }
}
