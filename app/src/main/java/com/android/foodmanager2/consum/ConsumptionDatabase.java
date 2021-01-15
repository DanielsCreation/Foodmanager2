package com.android.foodmanager2.consum;

import android.content.Context;
import android.os.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Consumption.class}, version = 1, exportSchema = false)
public abstract class ConsumptionDatabase extends RoomDatabase {
    //Singleton
    public static ConsumptionDatabase INSTANCE;

    public abstract ConsumptionDao consumptionDao();

    public static synchronized ConsumptionDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ConsumptionDatabase.class, "consumption_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static ConsumptionDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new ConsumptionDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConsumptionDao consumptionDao;

        private PopulateDbAsyncTask(ConsumptionDatabase db) {
            consumptionDao = db.consumptionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            consumptionDao.insert(new Consumption("El Pomodoro", "Brot",1, "mach warm"));
            consumptionDao.insert(new Consumption("Spagetti Italiano", "Penne", 12, "mach kalt"));
            consumptionDao.insert(new Consumption("Pizza Pasta", "Pasta Sauce", 4, "mach weg"));
            consumptionDao.insert(new Consumption("Milch macht müde männer munter", "Milch", 3, "mach her"));
            return null;
        }
    }
}
