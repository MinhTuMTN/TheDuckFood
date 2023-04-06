package com.theduckfood.model.response;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpResponse {
    private boolean error;
    private String message;
    private UserAccount userAccount;
    private UserProfile userProfile;
    private String authToken;
}
