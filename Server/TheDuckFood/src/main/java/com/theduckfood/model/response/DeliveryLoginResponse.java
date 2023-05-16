package com.theduckfood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryLoginResponse {
    private boolean error;
    private String message;
    private String deliveryManName;
    private String authToken;
}
