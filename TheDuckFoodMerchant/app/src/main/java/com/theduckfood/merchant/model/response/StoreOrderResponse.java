package com.theduckfood.merchant.model.response;


import com.theduckfood.merchant.model.Order;

import java.io.Serializable;
import java.util.List;

public class StoreOrderResponse implements Serializable {
    private Order order;
    private String address;
    private String userName;
    private String deliverManName;
    List<OrderItemResponse> orderItems;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeliverManName() {
        return deliverManName;
    }

    public void setDeliverManName(String deliverManName) {
        this.deliverManName = deliverManName;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}
