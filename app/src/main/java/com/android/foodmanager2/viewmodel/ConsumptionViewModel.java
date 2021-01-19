package com.android.foodmanager2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsumptionViewModel extends ViewModel {

    private MutableLiveData<String> mDate;
    private Calendar cal = Calendar.getInstance();
    private Calendar cal_tomorrow = Calendar.getInstance();
    private Calendar cal_yesterday = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final String today = simpleDateFormat.format(cal.getTime());
    private final String tomorrow = getTomorrow();
    private final String yesterday = getYesterday();
    public ConsumptionViewModel() {
        mDate = new MutableLiveData<>();
    }

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
}
