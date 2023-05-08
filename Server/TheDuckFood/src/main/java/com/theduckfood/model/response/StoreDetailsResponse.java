package com.theduckfood.model.response;

import com.theduckfood.entity.Food;
import com.theduckfood.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreDetailsResponse {
    private boolean error;
    private String message;
    private Store store;
    private List<Food> foods;
}
