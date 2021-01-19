package com.android.foodmanager2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.foodmanager2.model.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("DELETE FROM ingredient_table")
    void deleteAllIngredients();

    @Query("SELECT * FROM ingredient_table ORDER BY ingredientId DESC")
    LiveData<List<Ingredient>> getAllIngredients();
}
