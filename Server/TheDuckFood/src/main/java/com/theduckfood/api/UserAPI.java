package com.theduckfood.api;

import com.theduckfood.entity.UserProfile;
import com.theduckfood.model.UserProfileListResponse;
import com.theduckfood.model.UserProfileResponse;
import com.theduckfood.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user-profile-list")
    public UserProfileListResponse findAllUsers() {
        List<UserProfile> userProfileList = userRepository.findAllUserProfile();
        if (userProfileList != null && userProfileList.size() > 0)
            return new UserProfileListResponse(false, "Success", userProfileList);
        return new UserProfileListResponse(true, "Failed", null);
    }

    @GetMapping("/user-profile/{email}")
    public ResponseEntity<UserProfileResponse> findUserProfileByEmail(
            @PathVariable(value = "email") String email) {
        UserProfile userProfile = userRepository.findUserProfileByEmail(email);
        if (userProfile != null)
            return ResponseEntity.ok().body(new UserProfileResponse(false, "Success", userProfile));
        return  ResponseEntity.status(404).body(new UserProfileResponse(true, "Not found", null));
    }
}
