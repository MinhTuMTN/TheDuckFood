package com.theduckfood.model.response;

import com.theduckfood.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetListStore {
    private boolean error;
    private String message;
    List<Store> stores;
}
