package com.theduckfood.model.response;

import com.theduckfood.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponse {
    private boolean error;
    private String message;
    private List<UserAddress> userAddresses;
}
