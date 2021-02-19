package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ioThread implements Executor {
    private static final String TAG = "ioThread";
    private Executor IO_EXECUTOR = Executors.newSingleThreadExecutor();
    @Override
    public void execute(Runnable command) {
        Log.d(TAG, "execute: in here");
        IO_EXECUTOR.execute(command);
    }
}
