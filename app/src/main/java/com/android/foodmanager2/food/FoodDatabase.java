package com.android.foodmanager2.food;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Food.class}, version = 11, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {
    //Singleton
    public static FoodDatabase INSTANCE;

    public abstract FoodDao foodDao();

    public static synchronized FoodDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDatabase.class, "food_database")
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
        private FoodDao foodDao;

        private PopulateDbAsyncTask(FoodDatabase db) {
            foodDao = db.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.insert(new Food("Milch", "Weihenstephan",  "1","Liter", 1,"1", "1", "1", "1", "1", "1", "normal", true,true ,true,true));
            foodDao.insert(new Food("Mehrkornbrot", "Harry",  "500","Gramm",1, "1", "1", "1", "1", "1", "1", "normal", true, true,true ,true));
            foodDao.insert(new Food("Spaghetti", "Barilla",  "500","Gramm", 1,  "1", "1", "1", "1", "1", "1", "normal", true, true,true ,true));
            foodDao.insert(new Food("Pasta Sauce", "Miracoli",  "400","Gramm",  1, "1","1", "1", "1", "1", "1", "normal", true, true,true ,true));
            return null;
        }
    }
}
