package com.android.foodmanager2.recipe;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    //Singleton
    public static RecipeDatabase INSTANCE;

    public abstract RecipeDao recipeDao();

    public static synchronized RecipeDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class, "recipe_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static RecipeDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new RecipeDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        private PopulateDbAsyncTask(RecipeDatabase db) {
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.insert(new Recipe("El Pomodoro", "Brot",1, "mach warm"));
            recipeDao.insert(new Recipe("Spagetti Italiano", "Penne", 12, "mach kalt"));
            recipeDao.insert(new Recipe("Pizza Pasta", "Pasta Sauce", 4, "mach weg"));
            recipeDao.insert(new Recipe("Milch macht müde männer munter", "Milch", 3, "mach her"));
            return null;
        }
    }
}