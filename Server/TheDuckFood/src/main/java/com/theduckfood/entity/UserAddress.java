package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAddressId;

    private float latitude;
    private float longitude;
    private String streetAddress;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public UserAddress(float latitude, float longitude, String streetAddress, UserProfile userProfile) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.userProfile = userProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserProfile userProfile;

    @OneToMany(mappedBy = "userAddress")
    @JsonBackReference
    private List<Order> orders;

}
