package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.entity.UserProfile;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @Email
    private String email;

    private String password;
    private String status = Constants.USER_ACCOUNT_STATUS_ACTIVATED;

    private String otp;
    private String otpCreatedAt;
    private String otpWrongCount;
    private Date otpLastAttempt;

    private Date updatedAt = new Date();

    public UserAccount(String email, String password, UserProfile user) {
        this.email = email;
        this.password = password;
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonBackReference
    private UserProfile user;

}
