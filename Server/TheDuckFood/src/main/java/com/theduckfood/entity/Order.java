package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Double amount = 0D;
    private Double shipFee = 15000D;
    private Double serviceFee = 2000D;


    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    private String status = Constants.ORDER_STATUS_PROCESSING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserAddress userAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DeliveryMan deliveryMan;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonBackReference
    private Review review;

    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Store store, UserProfile userProfile, UserAddress userAddress, DeliveryMan deliveryMan) {
        this.store = store;
        this.userProfile = userProfile;
        this.userAddress = userAddress;
        this.deliveryMan = deliveryMan;
    }
}
