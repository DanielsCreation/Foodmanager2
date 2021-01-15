package com.android.foodmanager2.food;

import androidx.room.PrimaryKey;

import java.util.Date;

public class FoodItem extends Food {

    @PrimaryKey(autoGenerate = true)
    private int id_item;

    private Date bbd;

    private float price;

    private String store;

    public FoodItem(String name, String brand, String contents, String unit, float quantity, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree, Date bbd, float price, String store) {
        super(name, brand, contents, unit, quantity, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
        this.bbd = bbd;
        this.price = price;
        this.store = store;
    }

    public void setId(int id_item) {
        this.id_item = id_item;
    }

    public int getId() {
        return id_item;
    }

    public Date getBbd() {
        return bbd;
    }

    public float getPrice() {
        return price;
    }

    public String getStore() {
        return store;
    }

}
