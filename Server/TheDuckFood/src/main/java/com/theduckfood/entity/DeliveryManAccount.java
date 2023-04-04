package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryManAccount {
    @Id
    @Email
    private String email;

    private String password;
    private String status = Constants.DELIVERY_MAN_ACCOUNT_STATUS_ACTIVATED;
    private String otp;
    private String otpCreatedAt;
    private String otpWrongCount;
    private Date otpLastAttempt;

    private Date updatedAt = new Date();

    @OneToOne
    @JoinColumn(name = "deliveryManId", referencedColumnName = "deliveryManId")
    @JsonBackReference
    private DeliveryMan deliveryMan;

    public DeliveryManAccount(String email, String password, DeliveryMan deliveryMan) {
        this.email = email;
        this.password = password;
        this.deliveryMan = deliveryMan;
    }
}
