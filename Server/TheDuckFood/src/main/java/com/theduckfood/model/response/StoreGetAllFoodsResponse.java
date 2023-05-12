package com.theduckfood.model.response;

import com.theduckfood.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreGetAllFoodsResponse {
    private boolean error;
    private String message;
    private List<Food> foods;
}
