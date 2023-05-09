package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private String couponCode;
    private Long storeId;
    private Long userAddressId;
    private List<FoodOrderItemRequest> foods;
}
