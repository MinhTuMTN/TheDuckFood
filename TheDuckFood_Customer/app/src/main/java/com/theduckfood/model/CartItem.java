package com.theduckfood.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Food food;
    private int amount;

    public CartItem(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public CartItem() {
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
