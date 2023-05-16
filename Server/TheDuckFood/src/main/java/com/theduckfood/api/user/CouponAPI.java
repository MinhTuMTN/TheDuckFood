package com.theduckfood.api.user;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Store;
import com.theduckfood.model.response.GetCouponsResponse;
import com.theduckfood.repositories.CouponRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponAPI {
    @Autowired
    CouponRepository couponRepository;

    @Autowired
    StoreRepository storeRepository;
    @GetMapping
    public ResponseEntity<GetCouponsResponse> getCouponsByStoreId(
            @RequestParam("storeId") Long storeId
    ) {
        Store store = storeRepository.getStoreByStoreIdAndStatusNotContains(
                storeId,
                Constants.STORE_STATUS_DELETED
        );
        if (store == null)
            return ResponseEntity.status(404).body(new GetCouponsResponse(
                    true,
                    "Không tìm thấy cửa hàng",
                    null
            ));

        List<Coupon> coupons = couponRepository.getCouponsAvailableByStore(store);
        return ResponseEntity.ok(new GetCouponsResponse(
                false,
                "Thành công",
                coupons
        ));
    }
}
