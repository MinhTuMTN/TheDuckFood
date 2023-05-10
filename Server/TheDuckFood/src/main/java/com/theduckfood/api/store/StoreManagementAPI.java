package com.theduckfood.api.store;

import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.entity.UserAccount;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.model.request.StoreLoginRequest;
import com.theduckfood.model.response.GetStoreProfileResponse;
import com.theduckfood.model.response.ProfileResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreLoginResponse;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.EncodingUtil;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/merchant")
public class StoreManagementAPI {
    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Autowired
    StoreRepository storeRepository;
    @PostMapping("/login")
    public ResponseEntity<StoreLoginResponse> storeLogin (
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
            @RequestHeader("Authorization") String bearerToken)
    {
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
                            null
                    ));
        Store store = storeAccount.getStore();
        return ResponseEntity
                .ok(new GetStoreProfileResponse(
                        false,
                        "Thành công",
                        store));
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
        }
        else
            store.setStatus(Constants.STORE_STATUS_CLOSED);
        storeRepository.save(store);
        return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật thành công"));
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
