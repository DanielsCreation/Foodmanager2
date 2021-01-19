package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "home_table")
public class Home {

    @PrimaryKey(autoGenerate = true)
    private int homeId;

    private int purchaseId;

    private float homeQuantity;


    public Home(int purchaseId, float homeQuantity) {
        this.purchaseId = purchaseId;
        this.homeQuantity = homeQuantity;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public int getHomeId() {
        return homeId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public float getHomeQuantity() {
        return homeQuantity;
    }


}
