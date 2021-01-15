package com.android.foodmanager2.consum;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ConsumptionDao {

    @Insert
    void insert(Consumption consumption);

    @Update
    void update(Consumption consumption);

    @Delete
    void delete(Consumption consumption);

    @Query("DELETE FROM consumption_table")
    void deleteAllConsumptions();

    @Query("SELECT * FROM consumption_table ORDER BY id DESC")
    LiveData<List<Consumption>> getAllConsumptions();
}

