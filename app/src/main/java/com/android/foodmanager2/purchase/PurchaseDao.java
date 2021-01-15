package com.android.foodmanager2.purchase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PurchaseDao {

    @Insert
    void insert(Purchase purchase);

    @Update
    void update(Purchase purchase);

    @Delete
    void delete(Purchase purchase);

    @Query("DELETE FROM purchase_table")
    void deleteAllPurchases();

    @Query("SELECT * FROM purchase_table ORDER BY id DESC")
    LiveData<List<Purchase>> getAllPurchases();
}