package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
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
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Nationalized
    private String storeName;

    private String address;

    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number format")
    private String phone;

    private Double balance = 0D;
    private String avatar;

    private float rate = 5;
    private Long reviewCount = 0L;

    private String status = Constants.STORE_STATUS_CLOSED;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String fcmToken = null;

    @OneToOne(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonBackReference
    private StoreAccount storeAccount;

    @OneToMany(mappedBy = "store")
    @JsonBackReference
    private List<UserFavorites> userFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @JsonBackReference
    private List<Food> foods = new ArrayList<>();

    public Store(String storeName, String address, String phone, String avatar) {
        this.storeName = storeName;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
    }
}
