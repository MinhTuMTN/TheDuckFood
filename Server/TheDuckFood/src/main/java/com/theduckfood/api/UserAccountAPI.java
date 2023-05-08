package com.theduckfood.api;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.response.LoginResponse;
import com.theduckfood.model.response.ProfileResponse;
import com.theduckfood.model.response.SignUpResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.repositories.UserAccountRepository;
import com.theduckfood.repositories.UserProfileRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.EncodingUtil;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
                        EncodingUtil.encoding(loginRequest.getPassword()),
                        Constants.USER_ACCOUNT_STATUS_ACTIVATED
                );

        if (userAccount == null)
            return  ResponseEntity.status(401).body(new LoginResponse(true,
                    "Tài khoản hoặc mật khẩu không chính xác",
                    null,
                    null));
        return ResponseEntity.status(200).body(new LoginResponse(false,
                "Đăng nhập thành công",
                userAccount.getUser(),
                JWTUtil.generateJWTToken(userAccount.getEmail(), "user")));
    }

    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        if (userAccountRepository.findUserAccountByEmail(signUpRequest.getEmail()) != null ||
        userProfileRepository.findUserProfileByPhone(signUpRequest.getPhone()) != null)
            return ResponseEntity.status(409).body(new SignUpResponse(true,
                    "Email hoặc số điện thoại đã tồn tài",
                    null,
                    null,
                    null));

        UserProfile userProfile = userProfileRepository.save(
                new UserProfile(
                        signUpRequest.getPhone(),
                        signUpRequest.getFullName()));

        UserAccount userAccount = userAccountRepository.save(
                new UserAccount(
                        signUpRequest.getEmail(),
                        EncodingUtil.encoding(signUpRequest.getPassword()),
                        userProfile));

        userAccount.setPassword(signUpRequest.getPassword());
        return ResponseEntity.ok().body(new SignUpResponse(false,
                "Đăng ký thành công",
                userAccount,
                userProfile,
                JWTUtil.generateJWTToken(signUpRequest.getEmail(), "user")));
    }

    @PostMapping("/update-fcm-token")
    public ResponseEntity<SimpleMessageResponse> updateFCMToken(@RequestHeader("Authorization") String bearerToken,
                                                                @RequestBody String fcmToken) {
        try {
            String fcmTokenValue = fcmToken.replace("fcmToken=", "");
//            if (fcmTokenValue == null)
//                return ResponseEntity.ok(new SimpleMessageResponse(true, "Vui lòng cung cấp fcmToken"));

            String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
            UserAccount userAccount = userAccountRepository.findUserAccountByEmail(email);
            userAccount.getUser().setFcmToken(fcmTokenValue);
            userAccountRepository.save(userAccount);
            return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật FCM Token thành công"));
        } catch (Exception e) {
            return ResponseEntity.ok(new SimpleMessageResponse(true, e.getMessage()));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<SimpleMessageResponse> changePassword(@RequestHeader("Authorization") String bearerToken,
                                                                @RequestBody ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRepeatPassword()))
            return ResponseEntity.status(400).body(new SimpleMessageResponse(true, "Mật khẩu không khớp"));

        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserAccount userAccount = userAccountRepository.findUserAccountByEmail(email);

        if (userAccount == null)
            return ResponseEntity.status(400).body(new SimpleMessageResponse(true, "Đã có lỗi xảy ra"));
        else if (!EncodingUtil.isValid(changePasswordRequest.getOldPassword(), userAccount.getPassword()))
            return ResponseEntity.status(401).body(new SimpleMessageResponse(true, "Mật khẩu không đúng"));

        userAccount.setPassword(EncodingUtil.encoding(changePasswordRequest.getNewPassword()));
        userAccountRepository.save(userAccount);

        return ResponseEntity.ok(new SimpleMessageResponse(false, "Đổi mật khẩu thành công"));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@RequestHeader("Authorization") String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserAccount userAccount = userAccountRepository.findUserAccountsByEmailAndStatus(email, Constants.USER_ACCOUNT_STATUS_ACTIVATED);
        if (userAccount == null)
            return ResponseEntity.status(400).body(new ProfileResponse(true, "Đã có lỗi xảy ra", null, null));
        UserProfile userProfile = userProfileRepository.findUserProfileByUserId(userAccount.getUser().getUserId());
        return ResponseEntity.ok(new ProfileResponse(false, "Thành công", userAccount, userProfile));
    }

    @GetMapping
    public String payment(){
        return "Success";
    }
}
