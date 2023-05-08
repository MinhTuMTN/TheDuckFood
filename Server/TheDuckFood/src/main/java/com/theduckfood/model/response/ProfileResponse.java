package com.theduckfood.model.response;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileResponse {
    private boolean error;
    private String message;
    private UserAccount userAccount;
    private UserProfile userProfile;
}
