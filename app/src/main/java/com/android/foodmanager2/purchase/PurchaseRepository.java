package com.android.foodmanager2.purchase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PurchaseRepository {
    private PurchaseDao purchaseDao;
    public PurchaseRepository(Application application){
        PurchaseDatabase database = PurchaseDatabase.getInstance(application);
        purchaseDao = database.purchaseDao();
    }

    public void insert(Purchase purchase){
        new InsertPurchaseAsyncTask(purchaseDao).execute(purchase);
    }

    public void update(Purchase purchase){
        new InsertPurchaseAsyncTask(purchaseDao).execute(purchase);
    }

    public void delete(Purchase purchase){
        new InsertPurchaseAsyncTask(purchaseDao).execute(purchase);
    }

    public void deleteAllPurchase(){
        new InsertPurchaseAsyncTask(purchaseDao).execute();
    }

    public LiveData<List<Purchase>> getAllPurchase() {
        return purchaseDao.getAllPurchases();
    }


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
    private static class UpdateRecipeAsyncTask extends AsyncTask<Purchase, Void, Void> {
        private PurchaseDao purchaseDao;

        private UpdateRecipeAsyncTask(PurchaseDao purchaseDao) {
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
    private static class DeleteAllPurchaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private PurchaseDao purchaseDao;

        private DeleteAllPurchaseAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            purchaseDao.deleteAllPurchases();
            return null;
        }
    }
}
