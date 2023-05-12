package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreGetOrdersResponse {
    private boolean error;
    private String message;
    private String storeName;
    private List<StoreOrderResponse> orders;
}
