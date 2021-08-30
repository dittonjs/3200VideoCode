package com.example.databinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    private MutableLiveData<Integer> count = new MutableLiveData<>();

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    public CountViewModel() {
        count.setValue(0);
    }

    public void incrementCount() {
        count.setValue(count.getValue() + 1);
    }

    public void decrementCount() {
        count.setValue(count.getValue() - 1);
    }
}
