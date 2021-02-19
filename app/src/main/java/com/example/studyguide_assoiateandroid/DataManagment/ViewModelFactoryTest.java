package com.example.studyguide_assoiateandroid.DataManagment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

//******* tutorial - https://developer.android.com/codelabs/kotlin-android-training-view-model#7

// video tutorial - https://www.youtube.com/watch?v=8v_B-z0_6Yk time - 21:00
public class ViewModelFactoryTest implements ViewModelProvider.Factory {

    private int score;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return null;
    }

}
