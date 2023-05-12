package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
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
