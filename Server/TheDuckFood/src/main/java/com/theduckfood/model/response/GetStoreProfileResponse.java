package com.theduckfood.model.response;

import com.theduckfood.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreProfileResponse {
    private boolean error;
    private String message;
    private Store store;
}
