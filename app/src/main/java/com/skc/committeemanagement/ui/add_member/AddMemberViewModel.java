package com.skc.committeemanagement.ui.add_member;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMemberViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddMemberViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}
