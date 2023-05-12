package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderResponse {
    private Order order;
    private String address;
    private String userName;
    private String deliverManName;
    List<OrderItemResponse> orderItems;
}
