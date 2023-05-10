package com.theduckfood.model.response;

import com.theduckfood.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodDetailsResponse {
    private boolean error;
    private String message;
    private Food food;
}
