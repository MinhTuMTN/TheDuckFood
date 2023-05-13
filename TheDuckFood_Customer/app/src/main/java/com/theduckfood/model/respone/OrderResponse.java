package com.theduckfood.model.respone;

import com.theduckfood.model.Order;

import java.util.List;

public class OrderResponse {
    private Order order;
    private String address;
    private List<OrderItemResponse> orderItems;

    public OrderResponse() {
    }

    public OrderResponse(Order order, String address, List<OrderItemResponse> orderItems) {
        this.order = order;
        this.address = address;
        this.orderItems = orderItems;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}
