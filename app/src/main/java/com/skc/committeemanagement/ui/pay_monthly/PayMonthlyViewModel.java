package com.skc.committeemanagement.ui.pay_monthly;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayMonthlyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PayMonthlyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is monthly pay fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}