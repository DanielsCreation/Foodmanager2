package com.android.foodmanager2.consum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsumptionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsumptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is verzehr fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}