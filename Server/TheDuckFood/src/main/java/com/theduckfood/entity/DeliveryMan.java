package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryManId;

    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phone;

    @Nationalized
    private String fullName;

    @PositiveOrZero
    private Double balance = 0D;

    private String status = Constants.DELIVERY_MAN_STATUS_OFF;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String fcmToken = null;

    @OneToOne(mappedBy = "deliveryMan", fetch = FetchType.LAZY)
    @JsonBackReference
    private DeliveryManAccount deliveryManAccount;

    @OneToMany(mappedBy = "deliveryMan")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();

    public DeliveryMan(String phone, String fullName) {
        this.phone = phone;
        this.fullName = fullName;
    }
}
