package com.android.foodmanager2.home;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HomeRepository {
    private HomeDao homeDao;
    public HomeRepository(Application application){
        HomeDatabase database = HomeDatabase.getInstance(application);
        homeDao = database.homeDao();
    }

    public void insert(Home home){
        new HomeRepository.InsertHomeAsyncTask(homeDao).execute(home);
    }

    public void update(Home home){
        new HomeRepository.InsertHomeAsyncTask(homeDao).execute(home);
    }

    public void delete(Home home){
        new HomeRepository.InsertHomeAsyncTask(homeDao).execute(home);
    }

    public void deleteAllHomes(){
        new HomeRepository.InsertHomeAsyncTask(homeDao).execute();
    }

    public LiveData<List<Home>> getAllHomes() {
        return homeDao.getAllHomes();
    }


    private static class InsertHomeAsyncTask extends AsyncTask<Home, Void, Void> {
        private HomeDao homeDao;

        private InsertHomeAsyncTask(HomeDao homeDao){
            this.homeDao = homeDao;
        }

        @Override
        protected Void doInBackground(Home... home){
            homeDao.insert(home[0]);
            return null;
        }
    }
    private static class UpdateHomeAsyncTask extends AsyncTask<Home, Void, Void> {
        private HomeDao homeDao;

        private UpdateHomeAsyncTask(HomeDao homeDao) {
            this.homeDao = homeDao;
        }

        @Override
        protected Void doInBackground(Home... home) {
            homeDao.update(home[0]);
            return null;
        }
    }
    private static class DeleteHomeAsyncTask extends AsyncTask<Home, Void, Void> {
        private HomeDao homeDao;

        private DeleteHomeAsyncTask(HomeDao homeDao) {
            this.homeDao = homeDao;
        }

        @Override
        protected Void doInBackground(Home... home) {
            homeDao.delete(home[0]);
            return null;
        }
    }
    private static class DeleteAllHomeAsyncTask extends AsyncTask<Void, Void, Void> {
        private HomeDao homeDao;

        private DeleteAllHomeAsyncTask(HomeDao homeDao) {
            this.homeDao = homeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            homeDao.deleteAllHomes();
            return null;
        }
    }
}
