package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;

    private String name;

    private float persons;

    private String description;

    public Recipe(String name, float persons, String description) {
        this.name = name;
        this.persons = persons;
        this.description = description;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPersons() {
        return persons;
    }
}
