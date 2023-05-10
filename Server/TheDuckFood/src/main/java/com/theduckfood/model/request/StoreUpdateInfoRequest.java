package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateInfoRequest {
    private String storeName;
    private String storePhone;
    private String storeAddress;
}
