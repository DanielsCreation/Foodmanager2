package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int ingredientId;

    private int recipeId;

    private int foodId;

    private float ingredientQuantity;

    public Ingredient(int recipeId, int foodId, float ingredientQuantity) {
        this.recipeId = recipeId;
        this.foodId = foodId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getFoodId() {
        return foodId;
    }

    public float getIngredientQuantity() {
        return ingredientQuantity;
    }
}
