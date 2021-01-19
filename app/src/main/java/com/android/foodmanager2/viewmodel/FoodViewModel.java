package com.android.foodmanager2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.repositories.FoodRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel{
    private FoodRepository repository;
    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
    }

    public void insert(Food food) {
        repository.insert(food);
    }

    public void update(Food food) {
        repository.update(food);
    }

    public void delete(Food food) {
        repository.delete(food);
    }

    public void deleteAllFoods() { repository.deleteAllFoods(); }

    public Food getFood(int foodId) {
        return repository.getFood(foodId);
    }

    public LiveData<List<Food>> getAllFoods() {
        return repository.getAllFoods();
    }

    public LiveData<List<Food>> getFoodsByName(String newText) { return repository.getFoodsByName(newText); }
}
