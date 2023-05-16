package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderResponse {
    private Order order;
    private String storeName;
    private String userName;

}
