package com.android.foodmanager2.model;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchase_table")
public class Purchase {

    @PrimaryKey(autoGenerate = true)
    private int purchaseId;

    private String purchaseDate;

    private String bbd;

    private String quantity;

    private boolean bought;

    @Embedded
    private Food food;

    public Purchase(String quantity, String bbd, String purchaseDate, boolean bought, Food food) {
        this.purchaseDate = purchaseDate;
        this.bbd = bbd;
        this.quantity = quantity;
        this.bought = bought;
        this.food = food;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {this.purchaseDate = purchaseDate;}

    public String getBbd() {
        return bbd;
    }

    public void setBbd(String bbd) {this.bbd = bbd;}

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {this.quantity = quantity;}

    public boolean getBought() {
        return bought;
    }

    public void setBought(boolean bought) {this.bought = bought;}

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
