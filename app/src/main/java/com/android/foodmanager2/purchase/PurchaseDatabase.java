package com.android.foodmanager2.purchase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Purchase.class}, version = 1, exportSchema = false)
public abstract class PurchaseDatabase extends RoomDatabase{
    //Singleton
    public static PurchaseDatabase INSTANCE;

    public abstract PurchaseDao purchaseDao();

    public static synchronized PurchaseDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PurchaseDatabase.class, "purchase_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static PurchaseDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PurchaseDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PurchaseDao purchaseDao;

        private PopulateDbAsyncTask(PurchaseDatabase db) {
            purchaseDao = db.purchaseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            purchaseDao.insert(new Purchase("El Pomodoro", "Brot",1, "mach warm"));
            purchaseDao.insert(new Purchase("Spagetti Italiano", "Penne", 12, "mach kalt"));
            purchaseDao.insert(new Purchase("Pizza Pasta", "Pasta Sauce", 4, "mach weg"));
            purchaseDao.insert(new Purchase("Milch macht müde männer munter", "Milch", 3, "mach her"));
            return null;
        }
    }
}

