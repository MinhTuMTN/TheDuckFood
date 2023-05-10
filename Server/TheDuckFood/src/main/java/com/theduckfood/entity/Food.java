package com.theduckfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theduckfood.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;


import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Nationalized
    private String foodName;
    private String description;
    private String image;
    private float price = 0f;
    private float pricePromotion = 0f;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String status = Constants.FOOD_STATUS_SELLING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Store store;

    @OneToMany(mappedBy = "food")
    @JsonBackReference
    private List<OrderItem> orderItems = new ArrayList<>();

    public Food(String foodName, String description, String image, float price, float pricePromotion) {
        this.foodName = foodName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.pricePromotion = pricePromotion;
    }
}
