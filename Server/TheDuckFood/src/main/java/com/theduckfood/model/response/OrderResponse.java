package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
import com.theduckfood.entity.OrderItem;
import com.theduckfood.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Order order;
    private String address;
    List<OrderItemResponse> orderItems;
}
