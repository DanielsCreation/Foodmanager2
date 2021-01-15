package com.android.foodmanager2.food;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class FoodRepository {
    private FoodDao foodDao;
    public FoodRepository(Application application){
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
    }

    public void insert(Food food){
        new InsertFoodAsyncTask(foodDao).execute(food);
    }

    public void update(Food food){
        new UpdateFoodAsyncTask(foodDao).execute(food);
    }

    public void delete(Food food){
        new DeleteFoodAsyncTask(foodDao).execute(food);
    }

    public void deleteAllFoods(){
        new DeleteAllFoodsAsyncTask(foodDao).execute();
    }

    public LiveData<List<Food>> getAllFoods() {
        return foodDao.getAllFoods();
    }


    public LiveData<List<Food>> getFilteredFoods(String newText) {
        return foodDao.getFilteredFoods(newText);
    }

    private static class InsertFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private InsertFoodAsyncTask(FoodDao foodDao){
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... food){
            foodDao.insert(food[0]);
            return null;
        }
    }
    private static class UpdateFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private UpdateFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... food) {
            foodDao.update(food[0]);
            return null;
        }
    }
    private static class DeleteFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private DeleteFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... food) {
            foodDao.delete(food[0]);
            return null;
        }
    }
    private static class DeleteAllFoodsAsyncTask extends AsyncTask<Void, Void, Void> {
        private FoodDao foodDao;

        private DeleteAllFoodsAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.deleteAllFoods();
            return null;
        }
    }

}
