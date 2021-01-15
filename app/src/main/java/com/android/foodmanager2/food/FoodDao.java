package com.android.foodmanager2.food;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
    void insert(Food food);

    @Update
    void update(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food_table")
    void deleteAllFoods();

    @Query("SELECT * FROM food_table ORDER BY id DESC")
    LiveData<List<Food>> getAllFoods();

    @Query("SELECT * FROM food_table WHERE lower(name) LIKE '%' || :newText || '%' ORDER BY id DESC")
    LiveData<List<Food>> getFilteredFoods(String newText);
}
