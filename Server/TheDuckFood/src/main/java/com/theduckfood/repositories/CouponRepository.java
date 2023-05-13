package com.theduckfood.repositories;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,  Long> {
    Coupon findCouponByCouponCodeAndStatusAndStore(String couponCode, String status, Store store);
    @Query("SELECT c FROM Coupon c " +
            "WHERE c.store = :store " +
            "AND c.used < c.amount " +
            "AND c.expiredAt >= GETDATE() ")
    List<Coupon> getCouponsAvailableByStore(Store store);
    @Query("SELECT c FROM Coupon c " +
            "WHERE c.store = :store " +
            "AND (c.expiredAt <= GETDATE()   OR c.amount <= c.used)")
    List<Coupon> getCouponsUnavailableByStore(Store store);
}
