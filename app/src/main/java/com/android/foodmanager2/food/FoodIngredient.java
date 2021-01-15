package com.android.foodmanager2.food;

public class FoodIngredient extends FoodData{

    float quantity;

    public FoodIngredient(String name, String brand, String unit, float quantity, String kcal, String protein, String carb, String fat, String sugar, String saturated, String storage, boolean vegetarian, boolean vegan, boolean glutenfree, boolean laktofree) {
        super(name, brand, unit, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
        this.quantity = quantity;
    }


    public float getQuantity() {return quantity;}
}

/*
intent.putExtra(AddEditFoodActivity.EXTRA_QUANTITY, food.getQuantity());
float quantity = data.getFloatExtra(AddEditFoodActivity.EXTRA_QUANTITY, 1.0f);
float quantity = data.getFloatExtra(AddEditFoodActivity.EXTRA_QUANTITY, 1.0f);
public static final String EXTRA_QUANTITY =
"com.android.foodmanager2.food.EXTRA_QUANTITY";
quantity_input = findViewById(R.id.quantity_input);
float quantity = Float.parseFloat(quantity_input.getText().toString());
data.putExtra(EXTRA_QUANTITY, quantity);
holder.textViewQuantity.setText(String.valueOf(currentFood.getQuantity()));
private TextView textViewQuantity;
textViewQuantity = itemView.findViewById(R.id.food_quantity_txt);
 */