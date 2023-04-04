package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreAccount {
    @Id
    private String email;

    private String password;
    private String status = Constants.STORE_ACCOUNT_STATUS_ACTIVATED;
    private Date updatedAt = new Date();

    @OneToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId")
    @JsonBackReference
    private Store store;

    public StoreAccount(String email, String password, Store store) {
        this.email = email;
        this.password = password;
        this.store = store;
    }
}
