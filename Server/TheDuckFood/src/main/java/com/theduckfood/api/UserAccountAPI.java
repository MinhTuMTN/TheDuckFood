package com.theduckfood.api;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.response.LoginResponse;
import com.theduckfood.model.response.SignUpResponse;
import com.theduckfood.repositories.UserAccountRepository;
import com.theduckfood.repositories.UserProfileRepository;
import com.theduckfood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAccountAPI {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserProfileRepository userProfileRepository;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserAccount userAccount = userAccountRepository
                .findUserAccountByEmailAndPasswordAndStatus(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        Constants.USER_ACCOUNT_STATUS_ACTIVATED
                );

        if (userAccount == null)
            return  ResponseEntity.status(401).body(new LoginResponse(true,
                    "Tài khoản hoặc mật khẩu không chính xác",
                    null));
        return ResponseEntity.status(200).body(new LoginResponse(false,
                "Đăng nhập thành công",
                userAccount.getUser()));
    }

    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        if (userAccountRepository.findUserAccountByEmail(signUpRequest.getEmail()) != null ||
        userProfileRepository.findUserProfileByPhone(signUpRequest.getPhone()) != null)
            return ResponseEntity.status(409).body(new SignUpResponse(true,
                    "Email hoặc số điện thoại đã tồn tài",
                    null,
                    null));

        UserProfile userProfile = userProfileRepository.save(new UserProfile(signUpRequest.getPhone(), signUpRequest.getFullName()));

        UserAccount userAccount = userAccountRepository.save(new UserAccount(signUpRequest.getEmail(), signUpRequest.getPassword(), userProfile));
        return ResponseEntity.ok().body(new SignUpResponse(false,
                "Đăng ký thành công",
                userAccount,
                userProfile));
    }
    @GetMapping
    public String hello() {
        return "Hello";
    }
}
