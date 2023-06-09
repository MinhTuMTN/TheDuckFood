package com.theduckfood.api.store;

import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.entity.UserAccount;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.StoreLoginRequest;
import com.theduckfood.model.request.StoreUpdateInfoRequest;
import com.theduckfood.model.response.GetStoreProfileResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreLoginResponse;
import com.theduckfood.repositories.OrderRepository;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.EncodingUtil;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/merchant")
public class StoreManagementAPI {
    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/login")
    public ResponseEntity<StoreLoginResponse> storeLogin(
            @RequestBody StoreLoginRequest storeLoginRequest
    ) {
        StoreAccount storeAccount = storeAccountRepository
                .findStoreAccountByEmailAndPasswordAndStatusNotContaining(
                        storeLoginRequest.getEmail(),
                        EncodingUtil.encoding(storeLoginRequest.getPassword()),
                        Constants.STORE_STATUS_DELETED
                );
        if (storeAccount == null)
            return ResponseEntity.status(401).body(
                    new StoreLoginResponse(
                            true,
                            "Thông tin đăng nhập không chính xác",
                            null,
                            null
                    )
            );

        return ResponseEntity.ok(
                new StoreLoginResponse(
                        false,
                        "Đăng nhập thành công",
                        storeAccount.getStore(),
                        JWTUtil.generateJWTToken(storeAccount.getEmail(), "store")
                )
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<GetStoreProfileResponse> getStoreProfile(
            @RequestHeader("Authorization") String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        StoreAccount storeAccount = storeAccountRepository.findStoreAccountByEmailAndStatusNotContaining(
                email,
                Constants.STORE_STATUS_DELETED);
        if (storeAccount == null)
            return ResponseEntity
                    .status(400)
                    .body(new GetStoreProfileResponse(
                            true,
                            "Đã có lỗi xảy ra",
                            null,
                            0D,
                            0L
                    ));

        Store store = storeAccount.getStore();
        List<Object[]> results = orderRepository.findOrderAmountAndOrderCountToday(store);
        return ResponseEntity
                .ok(new GetStoreProfileResponse(
                        false,
                        "Thành công",
                        store,
                        (Double) results.get(0)[0] == null ? 0D : (Double) results.get(0)[0] ,
                        (Long) results.get(0)[1] == null ? 0L : (Long) results.get(0)[1]
                ));
    }

    @PostMapping("/update-profile")
    public ResponseEntity<SimpleMessageResponse> updateProfile(@RequestHeader("Authorization") String bearerToken,
                                                               @RequestBody StoreUpdateInfoRequest updateInfoRequest) {
        try {
            Store store = getStoreFromToken(bearerToken);
            if (store == null)
                throw new Exception();
            store.setStoreName(updateInfoRequest.getStoreName());
            store.setPhone(updateInfoRequest.getStorePhone());
            store.setAddress(updateInfoRequest.getStoreAddress());
            storeRepository.save(store);

            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Cập nhật thành công"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new SimpleMessageResponse(
                    true,
                    "Đã có lỗi xảy ra"));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<SimpleMessageResponse> changePassword(@RequestHeader("Authorization") String bearerToken,
                                                                @RequestBody ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRepeatPassword()))
            return ResponseEntity.status(400).body(new SimpleMessageResponse(true, "Mật khẩu không khớp"));

        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        StoreAccount storeAccount = storeAccountRepository
                .findStoreAccountByEmailAndStatusNotContaining(email, Constants.STORE_STATUS_DELETED);

        if (storeAccount == null)
            return ResponseEntity
                    .status(400)
                    .body(new SimpleMessageResponse(
                            true,
                            "Đã có lỗi xảy ra"));
        else if (!EncodingUtil.isValid(changePasswordRequest.getOldPassword(), storeAccount.getPassword()))
            return ResponseEntity.status(401).body(new SimpleMessageResponse(true, "Mật khẩu không đúng"));

        storeAccount.setPassword(EncodingUtil.encoding(changePasswordRequest.getNewPassword()));
        storeAccountRepository.save(storeAccount);

        return ResponseEntity.ok(new SimpleMessageResponse(false, "Đổi mật khẩu thành công"));
    }

    @GetMapping("/change-status")
    public ResponseEntity<SimpleMessageResponse> changeStatus(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("status") boolean status) {
        Store store = getStoreFromToken(bearerToken);
        if (store == null)
            return ResponseEntity
                    .status(401)
                    .body(new SimpleMessageResponse(true, "JWT Token không hợp lệ"));
        if (status) {
            store.setStatus(Constants.STORE_STATUS_OPENING);
        } else
            store.setStatus(Constants.STORE_STATUS_CLOSED);
        storeRepository.save(store);
        return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật thành công"));
    }

    @PostMapping("/update-fcm-token")
    public ResponseEntity<SimpleMessageResponse> updateFCMToken(@RequestHeader("Authorization") String bearerToken,
                                                                @RequestBody String fcmToken) {
        try {
            String fcmTokenValue = URLDecoder.decode(fcmToken, StandardCharsets.UTF_8).replace("fcmToken=", "");

            Store store = getStoreFromToken(bearerToken);
            assert store != null;
            store.setFcmToken(fcmTokenValue);
            storeRepository.save(store);
            return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật FCM Token thành công"));
        } catch (Exception e) {
            return ResponseEntity.ok(new SimpleMessageResponse(true, e.getMessage()));
        }
    }


    private Store getStoreFromToken(String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        StoreAccount storeAccount = storeAccountRepository.findStoreAccountByEmailAndStatusNotContaining(
                email,
                Constants.STORE_STATUS_DELETED);
        if (storeAccount == null)
            return null;

        return storeAccount.getStore();
    }
}
