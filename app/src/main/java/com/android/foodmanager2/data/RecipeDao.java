package com.android.foodmanager2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.foodmanager2.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAllRecipes();

    @Query("SELECT * FROM recipe_table ORDER BY recipeId DESC")
    LiveData<List<Recipe>> getAllRecipes();
}