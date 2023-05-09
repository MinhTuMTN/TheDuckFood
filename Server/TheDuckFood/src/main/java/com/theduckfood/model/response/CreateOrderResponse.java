package com.theduckfood.model.response;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderResponse {
    private boolean error;
    private String message;
    private Double amount_before_coupon;
    private Coupon coupon;
    private Order order;
}
