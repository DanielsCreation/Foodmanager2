package com.android.foodmanager2.recipe;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;
    public RecipeRepository(Application application){
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDao = database.recipeDao();
    }

    public void insert(Recipe recipe){
        new RecipeRepository.InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void update(Recipe recipe){
        new RecipeRepository.InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void delete(Recipe recipe){
        new RecipeRepository.InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void deleteAllRecipes(){
        new RecipeRepository.InsertRecipeAsyncTask(recipeDao).execute();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }


    private static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        private InsertRecipeAsyncTask(RecipeDao recipeDao){
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipe){
            recipeDao.insert(recipe[0]);
            return null;
        }
    }
    private static class UpdateRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        private UpdateRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipe) {
            recipeDao.update(recipe[0]);
            return null;
        }
    }
    private static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        private DeleteRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipe) {
            recipeDao.delete(recipe[0]);
            return null;
        }
    }
    private static class DeleteAllRecipeAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        private DeleteAllRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.deleteAllRecipes();
            return null;
        }
    }

}
