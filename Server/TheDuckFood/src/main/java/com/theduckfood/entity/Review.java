package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String reviewContent;
    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    @JsonBackReference
    private Order order;

    public Review(String reviewContent, int rate, UserProfile userProfile, Order order) {
        this.reviewContent = reviewContent;
        this.rate = rate;
        this.userProfile = userProfile;
        this.order = order;
    }
}
