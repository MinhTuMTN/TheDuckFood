package com.theduckfood.model;

import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileListResponse {
    private boolean error;
    private String message;
    private List<UserProfile> userProfileList;
}
