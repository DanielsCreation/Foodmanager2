package com.android.foodmanager2.consum;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ConsumptionRepository {
    private ConsumptionDao consumptionDao;
    public ConsumptionRepository(Application application){
        ConsumptionDatabase database = ConsumptionDatabase.getInstance(application);
        consumptionDao = database.consumptionDao();
    }

    public void insert(Consumption consumption){
        new ConsumptionRepository.InsertConsumptionAsyncTask(consumptionDao).execute(consumption);
    }

    public void update(Consumption consumption){
        new ConsumptionRepository.InsertConsumptionAsyncTask(consumptionDao).execute(consumption);
    }

    public void delete(Consumption consumption){
        new ConsumptionRepository.InsertConsumptionAsyncTask(consumptionDao).execute(consumption);
    }

    public void deleteAllConsumptions(){
        new ConsumptionRepository.InsertConsumptionAsyncTask(consumptionDao).execute();
    }

    public LiveData<List<Consumption>> getAllConsumptions() {
        return consumptionDao.getAllConsumptions();
    }


    private static class InsertConsumptionAsyncTask extends AsyncTask<Consumption, Void, Void> {
        private ConsumptionDao consumptionDao;

        private InsertConsumptionAsyncTask(ConsumptionDao consumptionDao){
            this.consumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(Consumption... consumption){
            consumptionDao.insert(consumption[0]);
            return null;
        }
    }
    private static class UpdateConsumptionAsyncTask extends AsyncTask<Consumption, Void, Void> {
        private ConsumptionDao consumptionDao;

        private UpdateConsumptionAsyncTask(ConsumptionDao consumptionDao) {
            this.consumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(Consumption... consumption) {
            consumptionDao.update(consumption[0]);
            return null;
        }
    }
    private static class DeleteConsumptionAsyncTask extends AsyncTask<Consumption, Void, Void> {
        private ConsumptionDao consumptionDao;

        private DeleteConsumptionAsyncTask(ConsumptionDao consumptionDao) {
            this.consumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(Consumption... consumption) {
            consumptionDao.delete(consumption[0]);
            return null;
        }
    }
    private static class DeleteAllConsumptionAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConsumptionDao consumptionDao;

        private DeleteAllConsumptionAsyncTask(ConsumptionDao consumptionDao) {
            this.consumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            consumptionDao.deleteAllConsumptions();
            return null;
        }
    }

}
