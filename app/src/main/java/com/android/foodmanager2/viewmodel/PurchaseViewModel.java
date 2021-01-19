package com.android.foodmanager2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Application;

import com.android.foodmanager2.model.Purchase;
import com.android.foodmanager2.repositories.PurchaseRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PurchaseViewModel extends AndroidViewModel {
    private PurchaseRepository repository;
    public PurchaseViewModel(@NonNull Application application) {
        super(application);
        repository = new PurchaseRepository(application);
        mDate = new MutableLiveData<>();
    }

    private MutableLiveData<String> mDate;
    private Calendar cal = Calendar.getInstance();
    private Calendar cal_tomorrow = Calendar.getInstance();
    private Calendar cal_yesterday = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final String today = simpleDateFormat.format(cal.getTime());
    private final String tomorrow = getTomorrow();
    private final String yesterday = getYesterday();


    public LiveData<String> setDate(String s){
        if (s.equals(today)) {
            mDate.setValue("heute");
        }
        else if (s.equals(tomorrow)) {
            mDate.setValue("morgen");
        }
        else if (s.equals(yesterday)) {
            mDate.setValue("gestern");
        }
        else {
            mDate.setValue(s);
        }
        return mDate;
    }

    private String getTomorrow() {
        cal_tomorrow.getTime();
        cal_tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        return simpleDateFormat.format(cal_tomorrow.getTime());
    }
    private String getYesterday() {
        cal_yesterday.getTime();
        cal_yesterday.add(Calendar.DAY_OF_MONTH, -1);
        return simpleDateFormat.format(cal_yesterday.getTime());
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

    public Purchase getPurchaseById(int purchaseId) {
        return repository.getPurchaseById(purchaseId);
    }

    public LiveData<List<Purchase>> getPurchasesByDate(String currentDate) { return repository.getPurchasesByDate(currentDate); }

    public LiveData<List<Purchase>> getAllPurchases() {
        return repository.getAllPurchases();
    }


}
