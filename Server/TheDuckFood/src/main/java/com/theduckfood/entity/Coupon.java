package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String couponCode;

    private Double minPrice;
    private Double maxDiscount;
    private float discount;
    private int amount;
    private int used = 0;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private Date startAt = new Date();
    private Date expiredAt = new Date();

    private String status = Constants.COUPON_STATUS_ACTIVATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Store store;

    @OneToMany(mappedBy = "coupon")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();

    public Coupon(String couponCode, Double minPrice, Double maxDiscount, int amount, Store store) {
        this.couponCode = couponCode;
        this.minPrice = minPrice;
        this.maxDiscount = maxDiscount;
        this.amount = amount;
        this.store = store;
    }
}
