package com.example.studyguide_assoiateandroid.AndroidCore.WorkManager;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.database.CursorJoiner;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.google.gson.Gson;

import java.util.List;

public class BlurViewModel extends AndroidViewModel {
    private static final String TAG = "BlurViewModel";
    private WorkManager workManager;

    // New instance variable for the WorkInfo class
    private LiveData<List<WorkInfo>> mSavedWorkInfo;

    public BlurViewModel(@NonNull Application application) {
        super(application);
        workManager = WorkManager.getInstance(application);
        mSavedWorkInfo = workManager.getWorkInfosByTagLiveData("SaveImageWorker");
    }

    // Add a getter method for mSavedWorkInfo
    LiveData<List<WorkInfo>> getOutputWorkInfo() { return mSavedWorkInfo; }


    void applyBlur(int blurLevel, Bitmap bitmap, Context context) {
        //workManager.enqueue(OneTimeWorkRequest.from(BlurWork.class))
        // pass bitmap as a Gson object
        Gson gson = new Gson();

        Data data = new Data.Builder()
                .putString("Gson Bitmap", gson.toJson(bitmap))
                .build();

        // Add WorkRequest to Cleanup temporary images
        WorkContinuation continuation = workManager.beginWith(OneTimeWorkRequest.from(CleanupWorker.class));

        /*
        use this when yo only want one instance of a worker at a time
        WorkContinuation continuation = mWorkManager
           ************.beginUniqueWork(IMAGE_MANIPULATION_WORK_NAME, *********************
                       ExistingWorkPolicy.REPLACE,
                       OneTimeWorkRequest.from(CleanupWorker.class));
         */

        // Add WorkRequests to blur the image the number of times requested
        for (int i = 0; i < blurLevel; i++) {
            OneTimeWorkRequest.Builder blurBuilder = new OneTimeWorkRequest.Builder(BlurWork.class);

            // Input the Uri if this is the first blur operation
            // After the first blur operation the input will be the output of previous
            // blur operations.
            if ( i == 0 ) {
                blurBuilder.setInputData(data);
            }

            continuation = continuation.then(blurBuilder.build());
        }

        // Add WorkRequest to save the image to the filesystem
        OneTimeWorkRequest save = new OneTimeWorkRequest.Builder(SaveImagetoFileWorker.class)
                .addTag("SaveImageWorker")
                .build();
        continuation = continuation.then(save);

        // Actually start the work
        continuation.enqueue();

        //*******************************************************************************************
        // return data

        // use (AppCompatActivity) context for lifeCycleOwner it implements android.arch.lifecycle.LifecycleOwner
//        continuation.getWorkInfosLiveData().observe((AppCompatActivity) context, new Observer<List<WorkInfo>>() {
//            @Override
//            public void onChanged(List<WorkInfo> workInfos) {
//                Log.d(TAG, "onChanged: "+ workInfos.size());
//            }
//        });

        //****************************use to get the returned data from a worker************************
      /*
      WorkManager.getInstance(myContext).getWorkInfoByIdLiveData(mathWork.getId())
    .observe(lifecycleOwner, info -> {
         if (info != null && info.getState().isFinished()) {
           int myResult = info.getOutputData().getInt(KEY_RESULT,
                  myDefaultValue));
           // ... do something with the result ...
         }
    });
       */
    }
}
