package com.android.foodmanager2.food;

public class FoodData {

    private String name;

    private String brand;

    private String unit;

    private String kcal;

    private String protein;

    private String carb;

    private String fat;

    private String sugar;

    private String saturated;

    private String storage;

    private boolean vegetarian;

    private boolean vegan;

    private boolean glutenfree;

    private boolean laktofree;

    public FoodData(String name, String brand, String unit, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree) {
        this.name = name;
        this.brand = brand;
        this.unit = unit;
        this.kcal = kcal;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
        this.sugar = sugar;
        this.saturated = saturated;
        this.storage = storage;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenfree = glutenfree;
        this.laktofree = laktofree;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getUnit() {
        return unit;
    }

    public String getKcal() {
        return kcal;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    public String getCarb() {
        return carb;
    }

    public String getSugar() {
        return sugar;
    }

    public String getSaturated() {
        return saturated;
    }

    public String getStorage() {
        return storage;
    }

    public boolean getVegetarian() {
        return vegetarian;
    }

    public boolean getVegan() {
        return vegan;
    }

    public boolean getGlutenfree() {
        return glutenfree;
    }

    public boolean getLaktofree() {
        return laktofree;
    }
}
