package com.theduckfood.api.store;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.model.response.StoreGetCouponsResponse;
import com.theduckfood.repositories.CouponRepository;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/merchant/coupon")
public class CouponManagementAPI {
    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Autowired
    CouponRepository couponRepository;
    @GetMapping
    public ResponseEntity<StoreGetCouponsResponse> getCoupons(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "status", required = false) Boolean status
    ) {
        Store store = getStoreFromToken(bearerToken);
        List<Coupon> coupons;
        if (status == null || !status)
            coupons = couponRepository.getCouponsUnavailableByStore(store);
        else
            coupons = couponRepository.getCouponsAvailableByStore(store);
        return ResponseEntity.ok(new StoreGetCouponsResponse(
                false,
                "Thành công",
                coupons
        ));
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
