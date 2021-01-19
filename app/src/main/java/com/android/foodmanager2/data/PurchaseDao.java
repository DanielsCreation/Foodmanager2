package com.android.foodmanager2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.foodmanager2.model.Purchase;

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

    @Query("SELECT * FROM purchase_table")
    LiveData<List<Purchase>> getAllPurchases();

    @Query("SELECT * FROM purchase_table WHERE purchaseDate = :purchaseId")
    Purchase getPurchaseById(int purchaseId);

    @Query("SELECT * FROM purchase_table WHERE purchaseDate = :currentDate ORDER BY purchaseId DESC")
    LiveData<List<Purchase>> getPurchasesByDate(String currentDate);
}