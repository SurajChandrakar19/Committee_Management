package com.skc.committeemanagement.ui.candidate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CandidateViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CandidateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is candidate fragment");
        mText.setValue("hello");
    }

    public LiveData<String> getText() {
        return mText;
    }
}