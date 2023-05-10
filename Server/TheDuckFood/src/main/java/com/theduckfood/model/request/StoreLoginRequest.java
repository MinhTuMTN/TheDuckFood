package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class StoreLoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
