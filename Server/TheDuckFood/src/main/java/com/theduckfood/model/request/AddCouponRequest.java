package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCouponRequest {
    private String couponCode;
    private float discountPercent;
    private Double maxDiscount;
    private Double minPrice;
    private int amount;
    private Date startAt;
    private Date expiredAt;
}
