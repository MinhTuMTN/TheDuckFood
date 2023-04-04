package com.theduckfood.api;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.model.UserProfileResponse;
import com.theduckfood.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @GetMapping
    public String hello() {
        return "Hello";
    }

//    @GetMapping("/user-profile-list")
//    public UserProfileListResponse findAllUsers() {
//        List<UserProfile> userProfileList = userRepository.findAllUserProfile();
//        if (userProfileList != null && userProfileList.size() > 0)
//            return new UserProfileListResponse(false, "Success", userProfileList);
//        return new UserProfileListResponse(true, "Failed", null);
//    }

//    @GetMapping("/user-profile/{email}")
//    public ResponseEntity<UserProfileResponse> findUserProfileByEmail(
//            @PathVariable(value = "email") String email) {
//        UserProfile userProfile = userAccountRepository.findByEmail(email).getUser();
//        if (userProfile != null)
//            return ResponseEntity.ok().body(new UserProfileResponse(false, "Success", userProfile));
//        return  ResponseEntity.status(404).body(new UserProfileResponse(true, "Not found", null));
//    }

    @GetMapping("/user-account/{email}")
    public ResponseEntity<UserProfileResponse> findUserProfileByEmail(
            @PathVariable(value = "email") String email) {
        UserAccount userProfile = userAccountRepository.findByEmail(email);
        if (userProfile != null)
            return ResponseEntity.ok().body(new UserProfileResponse(false, "Success", userProfile));
        return  ResponseEntity.status(404).body(new UserProfileResponse(true, "Not found", null));
    }
}
