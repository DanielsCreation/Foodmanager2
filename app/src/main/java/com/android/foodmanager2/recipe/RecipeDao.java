package com.android.foodmanager2.recipe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("DELETE FROM rezepte_table")
    void deleteAllRecipes();

    @Query("SELECT * FROM rezepte_table ORDER BY id DESC")
    LiveData<List<Recipe>> getAllRecipes();
}