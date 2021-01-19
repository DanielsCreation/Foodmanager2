package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.android.foodmanager2.model.Food;

public class FoodItem extends Food {

    @PrimaryKey(autoGenerate = true)
    private int id_item;

    private String bbd;

    private String date;

    private boolean bought;

    public FoodItem(String name, String brand, String store, String price, String contents, String unit, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree, String bbd, String date, boolean bought) {
        super(name, brand, store, price, contents, unit, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
        this.bbd = bbd;
        this.bought = bought;
        this.date = date;
    }

    public void setId(int id_item) {
        this.id_item = id_item;
    }

    public int getId() {
        return id_item;
    }

    public String getBbd() {
        return bbd;
    }

    private String getDate() {return date;}

    private boolean getBought() {return bought;}

}
