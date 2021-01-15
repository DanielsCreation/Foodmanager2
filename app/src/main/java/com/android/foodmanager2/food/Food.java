package com.android.foodmanager2.food;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food extends FoodIngredient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String contents;

    public Food(String name, String brand, String contents, String unit, float quantity, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree) {
        super(name, brand, unit, quantity, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
        this.contents = contents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

}