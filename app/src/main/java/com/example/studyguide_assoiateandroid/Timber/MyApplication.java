package com.example.studyguide_assoiateandroid.Timber;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import timber.log.Timber;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
