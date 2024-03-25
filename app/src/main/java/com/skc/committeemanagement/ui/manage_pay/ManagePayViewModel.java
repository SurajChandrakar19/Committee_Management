package com.skc.committeemanagement.ui.manage_pay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManagePayViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ManagePayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is member fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}