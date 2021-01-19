package com.android.foodmanager2.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.foodmanager2.model.Consumption;
import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.model.Ingredient;
import com.android.foodmanager2.model.Home;
import com.android.foodmanager2.model.Purchase;
import com.android.foodmanager2.model.Recipe;

@Database(entities = {Food.class, Purchase.class, Consumption.class, Home.class, Recipe.class, Ingredient.class}, version = 8, exportSchema = false)
public abstract class FoodManagerDatabase extends RoomDatabase {
    //Singleton
    public static FoodManagerDatabase INSTANCE;

    public abstract FoodDao foodDao();
    public abstract PurchaseDao purchaseDao();
    public abstract ConsumptionDao consumptionDao();
    public abstract HomeDao homeDao();
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();

    public static synchronized FoodManagerDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FoodManagerDatabase.class, "foodmanager_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final ConsumptionDao consumptionDao;
        private final HomeDao homeDao;
        private final FoodDao foodDao;
        private final PurchaseDao purchaseDao;
        private final RecipeDao recipeDao;
        private final IngredientDao ingredientDao;

        private PopulateDbAsyncTask(FoodManagerDatabase db) {
            foodDao = db.foodDao();
            purchaseDao = db.purchaseDao();
            consumptionDao = db.consumptionDao();
            homeDao = db.homeDao();
            recipeDao = db.recipeDao();
            ingredientDao = db.ingredientDao();
        }

        Food food1 = new Food("Milch", "Weihenstephan", "Edeka", "1,11",  "1","Liter", "1", "1", "1", "1", "1", "1", "normal", true,true ,true,true);
        Food food2 = new Food("Mehrkornbrot", "Harry", "Edeka", "1,11",  "500","Gramm","1", "1", "1", "1", "1", "1", "normal", true, true,true ,true);
        Food food3 = new Food("Spaghetti", "Barilla", "Edeka", "1,11",  "500","Gramm",  "1", "1", "1", "1", "1", "1", "normal", true, true,true ,true);
        Food food4 = new Food("Pasta Sauce", "Miracoli", "Edeka", "1,11",  "400","Gramm",  "1","1", "1", "1", "1", "1", "normal", true, true,true ,true);

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.insert(food1);
            foodDao.insert(food2);
            foodDao.insert(food3);
            foodDao.insert(food4);
            purchaseDao.insert(new Purchase( "1","17.01.2021", "01.02.2021",false, food1));
            purchaseDao.insert(new Purchase( "1","17.01.2021", "01.02.2021",false, food2));
            purchaseDao.insert(new Purchase( "1","17.01.2021", "01.02.2021",false, food3));
            purchaseDao.insert(new Purchase("1", "17.01.2021", "01.02.2021",false, food4));
            consumptionDao.insert(new Consumption(1, 0.5f,"01.01.2011"));
            consumptionDao.insert(new Consumption(1, 0.5f,"01.01.2011"));
            consumptionDao.insert(new Consumption(1, 0.5f,"01.01.2011"));
            consumptionDao.insert(new Consumption(1, 0.5f,"01.01.2011"));
            homeDao.insert(new Home(1, 1.0f));
            homeDao.insert(new Home(1, 1.0f));
            homeDao.insert(new Home(1, 1.0f));
            homeDao.insert(new Home(1, 1.0f));
            recipeDao.insert(new Recipe("El Pomodoro", 1, "mach warm"));
            recipeDao.insert(new Recipe("Spagetti Italiano",  12, "mach kalt"));
            recipeDao.insert(new Recipe("Pizza Pasta", 4, "mach weg"));
            recipeDao.insert(new Recipe("Milch macht müde männer munter",  3, "mach her"));
            ingredientDao.insert(new Ingredient( 1, 2, 0.5f));
            ingredientDao.insert(new Ingredient(  1, 3, 0.1f));
            ingredientDao.insert(new Ingredient( 1, 1, 0.2f));
            ingredientDao.insert(new Ingredient(2,  1,  0.3f));
            return null;
        }






    }
}

