package com.android.foodmanager2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consumption_table")
        public class Consumption {

    @PrimaryKey(autoGenerate = true)
    private int consumId;

    private int purchaseId;

    private float consumQuantity;

    private String consumDate;

    public Consumption(int purchaseId, float consumQuantity, String consumDate) {
        this.purchaseId = purchaseId;
        this.consumQuantity = consumQuantity;
        this.consumDate = consumDate;
    }

    public int getConsumId() {
        return consumId;
    }

    public void setConsumId(int consumId) {
        this.consumId = consumId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public float getConsumQuantity() {
        return consumQuantity;
    }

    public String getConsumDate() {
        return consumDate;
    }

}

