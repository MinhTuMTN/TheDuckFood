package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phone;

    @Nationalized
    private String fullName;
    private int points = 0;
    private Double balance = 0D;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private boolean isVerified = false;
    private String fcmToken = null;

    public UserProfile(String phone, String fullName) {
        this.phone = phone;
        this.fullName = fullName;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private UserAccount userAccount;

    @OneToMany(mappedBy = "userProfile")
    @JsonBackReference
    private List<UserAddress> userAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonBackReference
    private List<UserFavorites> userFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();

}
