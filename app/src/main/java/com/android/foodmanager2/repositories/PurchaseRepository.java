package com.android.foodmanager2.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.foodmanager2.data.PurchaseDao;
import com.android.foodmanager2.data.FoodManagerDatabase;
import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.model.Purchase;
import com.android.foodmanager2.view.PurchaseFragment;

import java.util.Collections;
import java.util.List;

public class PurchaseRepository {
    private PurchaseDao purchaseDao;
    public PurchaseRepository(Application application){
        FoodManagerDatabase database = FoodManagerDatabase.getInstance(application);
        purchaseDao = database.purchaseDao();
    }

    public void insert(Purchase purchase){ new InsertPurchaseAsyncTask(purchaseDao).execute(purchase); }

    public void update(Purchase purchase){ new UpdatePurchaseAsyncTask(purchaseDao).execute(purchase); }

    public void delete(Purchase purchase){ new DeletePurchaseAsyncTask(purchaseDao).execute(purchase); }

    public void deleteAllPurchases(){
        new DeleteAllPurchasesAsyncTask(purchaseDao).execute();
    }

    public LiveData<List<Purchase>> getPurchasesByNameDate(String currentDate, String newText) { return purchaseDao.getPurchasesByNameDate(currentDate, newText); }

    public LiveData<List<Purchase>> getAllPurchases() { return purchaseDao.getAllPurchases(); }

    public LiveData<List<Purchase>> getPurchasesByDate(String currentDate) { return purchaseDao.getPurchasesByDate(currentDate); }

    public LiveData<Purchase> getPurchaseById(int purchaseId) { return purchaseDao.getPurchaseById(purchaseId); }

    public LiveData<List<Purchase>> getPurchasesByName(String newText) { return purchaseDao.getPurchasesByName(newText); }


    private static class InsertPurchaseAsyncTask extends AsyncTask<Purchase, Void, Void> {
        private PurchaseDao purchaseDao;
        private InsertPurchaseAsyncTask(PurchaseDao purchaseDao){
            this.purchaseDao = purchaseDao;
        }
        @Override
        protected Void doInBackground(Purchase... purchase){
            purchaseDao.insert(purchase[0]);
            return null;
        }
    }

    private static class UpdatePurchaseAsyncTask extends AsyncTask<Purchase, Void, Void> {
        private PurchaseDao purchaseDao;
        private UpdatePurchaseAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }
        @Override
        protected Void doInBackground(Purchase... purchase) {
            purchaseDao.update(purchase[0]);
            return null;
        }
    }

    private static class DeletePurchaseAsyncTask extends AsyncTask<Purchase, Void, Void> {
        private PurchaseDao purchaseDao;
        private DeletePurchaseAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }
        @Override
        protected Void doInBackground(Purchase... purchase) {
            purchaseDao.delete(purchase[0]);
            return null;
        }
    }

    private static class DeleteAllPurchasesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PurchaseDao purchaseDao;
        private DeleteAllPurchasesAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao; }
        @Override
        protected Void doInBackground(Void... voids) {
            purchaseDao.deleteAllPurchases();
            return null;
        }
    }




}
