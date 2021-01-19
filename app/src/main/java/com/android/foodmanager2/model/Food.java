package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food extends FoodData {

    @PrimaryKey(autoGenerate = true)
    private int foodId;

    private String store;

    private String price;

    private String contents;


    public Food(String name, String brand, String store, String price, String contents, String unit, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree) {
        super(name, brand, unit, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
        this.store = store;
        this.price = price;
        this.contents = contents;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getStore() {
        return store;
    }

    public String getPrice() {
        return price;
    }

    public String getContents() {
        return contents;
    }

}