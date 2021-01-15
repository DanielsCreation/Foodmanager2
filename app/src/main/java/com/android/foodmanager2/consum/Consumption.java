package com.android.foodmanager2.consum;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consumption_table")
        public class Consumption {
    @PrimaryKey
    private int id;

    private String name;

    private String ingredient_1;

    private float quantity_1;

    private String description;

    public Consumption(String name, String ingredient_1, float quantity_1, String description) {
        this.name = name;
        this.ingredient_1 = ingredient_1;
        this.quantity_1 = quantity_1;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredient_1() {
        return ingredient_1;
    }

    public float getQuantity_1() {
        return quantity_1;
    }

    public String getDescription() {
        return description;
    }
}

