package com.theduckfood.model;

import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private boolean error;
    private String message;
    private UserProfile userProfile;
}
