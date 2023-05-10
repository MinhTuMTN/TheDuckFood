package com.theduckfood.repositories;

import com.theduckfood.entity.Coupon;
import com.theduckfood.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,  Long> {
    Coupon findCouponByCouponCodeAndStatusAndStore(String couponCode, String status, Store store);
}
