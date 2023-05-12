package com.theduckfood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetAllOrderResponse {
    private boolean error;
    private String message;
    private List<OrderResponse> orders;
}
