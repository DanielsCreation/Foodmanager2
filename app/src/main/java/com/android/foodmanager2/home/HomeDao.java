package com.android.foodmanager2.home;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HomeDao {

    @Insert
    void insert(Home purchase);

    @Update
    void update(Home purchase);

    @Delete
    void delete(Home purchase);

    @Query("DELETE FROM home_table")
    void deleteAllHomes();

    @Query("SELECT * FROM home_table ORDER BY id DESC")
    LiveData<List<Home>> getAllHomes();
}