package com.theduckfood.model.response;

import com.theduckfood.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCouponsResponse {
    private boolean error;
    private String message;
    private List<Coupon> coupons;
}
