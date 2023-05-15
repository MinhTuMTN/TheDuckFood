package com.theduckfood.model.response;

import com.theduckfood.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodSearchResponse {
    private Food food;
    private Long storeId;
    private String storeName;
    private float rate;
    private Long reviewCount;
}
