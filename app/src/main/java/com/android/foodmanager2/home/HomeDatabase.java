package com.android.foodmanager2.home;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Home.class}, version = 1, exportSchema = false)
public abstract class HomeDatabase extends RoomDatabase{
    //Singleton
    public static HomeDatabase INSTANCE;

    public abstract HomeDao homeDao();

    public static synchronized HomeDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HomeDatabase.class, "home_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static HomeDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new HomeDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private HomeDao homeDao;

        private PopulateDbAsyncTask(HomeDatabase db) {
            homeDao = db.homeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            homeDao.insert(new Home("El Pomodoro", "Brot",1, "mach warm"));
            homeDao.insert(new Home("Spagetti Italiano", "Penne", 12, "mach kalt"));
            homeDao.insert(new Home("Pizza Pasta", "Pasta Sauce", 4, "mach weg"));
            homeDao.insert(new Home("Milch macht müde männer munter", "Milch", 3, "mach her"));
            return null;
        }
    }
}
