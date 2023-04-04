package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
