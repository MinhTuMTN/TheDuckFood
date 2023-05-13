package com.theduckfood.api.store;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.model.request.AddCouponRequest;
import com.theduckfood.model.response.SimpleMessageResponse;
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


    @PostMapping
    public ResponseEntity<SimpleMessageResponse> addCoupon(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody AddCouponRequest couponRequest
            ) {
        try {
            Store store = getStoreFromToken(bearerToken);

            // Check coupon code
            for (int i = 0; i < couponRequest.getCouponCode().length(); i++)
                if (!Character.isLetterOrDigit(couponRequest.getCouponCode().charAt(i)))
                    throw new Exception("Coupon Code không được chứa ký tự đặc biệt");
            List<Coupon> availableCoupons = couponRepository.getCouponsAvailableByStore(store);
            for (Coupon coupon : availableCoupons)
                if (coupon.getCouponCode().equals(couponRequest.getCouponCode()))
                    throw new Exception("Coupon Code đã tồn tại");

            // Check discountPercent
            if (!(couponRequest.getDiscountPercent() >= 0.05 && couponRequest.getDiscountPercent() <= 0.5))
                throw new Exception("Phần trăm giảm phải từ 5% đến 50%");

            // Check Negative Value
            if (couponRequest.getMinPrice() < 0
                    || couponRequest.getMaxDiscount() < 0
                    || couponRequest.getAmount() < 0)
                throw new Exception("Dữ liệu không hợp lệ. Chỉ chấp nhận giá trị lớn hơn hoặc bằng 0");

            // Check Date
            if (couponRequest.getStartAt().compareTo(couponRequest.getExpiredAt()) >= 0)
                throw new Exception("Ngày kết thúc phải sau ngày bắt đầu ít nhất 1 ngày");


            Coupon coupon = new Coupon(
                    couponRequest.getCouponCode(),
                    couponRequest.getMinPrice(),
                    couponRequest.getMaxDiscount(),
                    couponRequest.getAmount(),
                    store
            );
            coupon.setDiscount(couponRequest.getDiscountPercent());
            coupon.setStartAt(couponRequest.getStartAt());
            coupon.setExpiredAt(couponRequest.getExpiredAt());

            couponRepository.save(coupon);
            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Thành công"
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new SimpleMessageResponse(
                            true,
                            e.getMessage() == null ? "Đã có lỗi xảy ra" : e.getMessage()
                    ));
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
