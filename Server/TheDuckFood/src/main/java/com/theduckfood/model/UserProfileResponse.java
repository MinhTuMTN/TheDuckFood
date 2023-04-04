package com.theduckfood.model;

import com.theduckfood.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private boolean error;
    private String message;
    private UserAccount userAccount;
}
