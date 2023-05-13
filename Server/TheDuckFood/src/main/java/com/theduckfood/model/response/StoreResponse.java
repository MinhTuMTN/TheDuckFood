package com.theduckfood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private Long storeId;
    private String storeName;
    private String storeAddress;
}
