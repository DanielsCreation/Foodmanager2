package com.android.foodmanager2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.model.Purchase;
import com.android.foodmanager2.repositories.PurchaseRepository;
import com.android.foodmanager2.util.MyCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class PurchaseViewModel extends AndroidViewModel {
    private Calendar calendar;
    private PurchaseRepository repository;
    public PurchaseViewModel(@NonNull Application application) {
        super(application);
        repository = new PurchaseRepository(application);
        mDate = new MutableLiveData<>();
    }

    private MutableLiveData<String> mDate;

    public LiveData<String> setDate(String s){
        if (s.equals(MyCalendar.getToday())) {
            mDate.setValue("heute");
        }
        else if (s.equals(MyCalendar.getTomorrow())) {
            mDate.setValue("morgen");
        }
        else if (s.equals(MyCalendar.getYesterday())) {
            mDate.setValue("gestern");
        }
        else {
            mDate.setValue(s);
        }
        return mDate;
    }


    public void insert(Purchase purchase) {
        repository.insert(purchase);
    }

    public void update(Purchase purchase) {
        repository.update(purchase);
    }

    public void delete(Purchase purchase) {
        repository.delete(purchase);
    }

    public void deleteAllPurchases() {
        repository.deleteAllPurchases();
    }

    public void getPurchaseById(int purchaseId) { repository.getPurchaseById(purchaseId); }

    public LiveData<List<Purchase>> getPurchasesByNameDate(String currentDate, String newText) { return repository.getPurchasesByNameDate(currentDate, newText); }

    public LiveData<List<Purchase>> getPurchasesByDate(String currentDate) { return repository.getPurchasesByDate(currentDate); }

    public LiveData<List<Purchase>> getAllPurchases() {
        return repository.getAllPurchases();
    }

    public LiveData<List<Purchase>> getPurchasesByName(String newText) { return repository.getPurchasesByName(newText); }


}
