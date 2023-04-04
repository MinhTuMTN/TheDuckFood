package com.theduckfood.model.response;

import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean error;
    private String message;
    private UserProfile userProfile;
}
