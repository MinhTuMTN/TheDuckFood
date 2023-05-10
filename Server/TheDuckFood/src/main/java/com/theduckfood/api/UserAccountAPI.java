package com.theduckfood.api;

import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserAddress;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.request.SignUpRequest;
import com.theduckfood.model.request.UpdateProfileRequest;
import com.theduckfood.model.response.*;
import com.theduckfood.repositories.UserAccountRepository;
import com.theduckfood.repositories.UserAddressRepository;
import com.theduckfood.repositories.UserProfileRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.EncodingUtil;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserAccountAPI {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserAddressRepository userAddressRepository;
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

    @PutMapping("/update-profile")
    public ResponseEntity<SimpleMessageResponse> updateProfile(@RequestHeader("Authorization") String bearerToken,
                                                                @RequestBody UpdateProfileRequest updateProfileRequest) {
        try {
            String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
            UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();
            userProfile.setFullName(updateProfileRequest.getFullName());
            userProfile.setPhone(updateProfileRequest.getPhone());
            userProfileRepository.save(userProfile);
            return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new SimpleMessageResponse(true, "Đã có lỗi xảy ra"));
        }
    }

    @PostMapping("/add-address")
    public ResponseEntity<SimpleMessageResponse> addAddress(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "streetAddress", required = true) String streetAddress) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();
        UserAddress userAddress = new UserAddress();
        userAddress.setStreetAddress(streetAddress);
        userAddress.setUserProfile(userProfile);
        userAddressRepository.save(userAddress);
        return ResponseEntity.ok(new SimpleMessageResponse(
                false,
                "Thêm địa chỉ thành công"
        ));
    }

    @GetMapping("/address")
    public ResponseEntity<AddressResponse> getAddresses(
            @RequestHeader("Authorization") String bearerToken
    ) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();
        List<UserAddress> userAddresses = userAddressRepository.findUserAddressesByUserProfileAndIsDeletedFalse(userProfile);
        return ResponseEntity.ok(new AddressResponse(
                false,
                "Thành công",
                userAddresses
        ));
    }

    private UserProfile getUserProfile(String bearerToken) {
        String email = Objects
                .requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken))
                .get("email")
                .toString();
        return userAccountRepository
                .findUserAccountByEmail(email)
                .getUser();
    }

    private UserAddress getUserAddress(String bearerToken, Long userAddressId) {
        UserProfile userProfile = getUserProfile(bearerToken);
        return userAddressRepository
                .findUserAddressesByUserAddressIdAndUserProfile(
                        userAddressId,
                        userProfile);
    }

    @DeleteMapping("/delete-address")
    public ResponseEntity<AddressResponse> deleteAddresses(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "userAddressId", required = true) Long userAddressId
    ) {
        UserAddress userAddress = getUserAddress(bearerToken, userAddressId);
        if (userAddress == null)
            return ResponseEntity.status(404).body(new AddressResponse(
                    true,
                    "Không tìm thấy địa chỉ này",
                    null
            ));
        userAddress.setDeleted(true);
        userAddress.setUpdatedAt(new Date());
        userAddressRepository.save(userAddress);

        List<UserAddress> userAddresses = userAddressRepository
                .findUserAddressesByUserProfileAndIsDeletedFalse(getUserProfile(bearerToken));
        return ResponseEntity.ok(new AddressResponse(
                false,
                "Thành công",
                userAddresses
        ));
    }

    @PutMapping("/update-address")
    public ResponseEntity<AddressResponse> updateAddress(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "userAddressId", required = true) Long userAddressId,
            @RequestParam(value = "streetAddress", required = true) String streetAddress) {
        UserAddress userAddress = getUserAddress(bearerToken, userAddressId);
        if (userAddress == null)
            return ResponseEntity.status(404).body(new AddressResponse(
                    true,
                    "Không tìm thấy địa chỉ này",
                    null
            ));
        userAddress.setStreetAddress(streetAddress);
        userAddress.setUpdatedAt(new Date());

        userAddressRepository.save(userAddress);
        List<UserAddress> userAddresses = userAddressRepository
                .findUserAddressesByUserProfileAndIsDeletedFalse(
                        getUserProfile(bearerToken)
                );

        return ResponseEntity.ok(new AddressResponse(
                false,
                "Cập nhật địa chỉ thành công",
                userAddresses
        ));
    }
}
