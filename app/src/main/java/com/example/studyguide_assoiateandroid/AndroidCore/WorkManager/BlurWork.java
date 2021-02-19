package com.example.studyguide_assoiateandroid.AndroidCore.WorkManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.studyguide_assoiateandroid.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BlurWork extends Worker {
    private static final String TAG = "BlurWork";

    private Context context;
    private Bitmap image;

    public BlurWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    public interface BitmapInterface{
        void getBitmap(Bitmap bitmap);
    }
    private BitmapInterface bitmapInterface;

    @NonNull
    @Override
    public Result doWork() {
        try {

            WorkerUtils.makeStatusNotification("Blurring image", context);
            WorkerUtils.sleep();
            // get image
            Data data = getInputData();
            Gson gson = new Gson();
            Type type = new TypeToken<Bitmap>(){}.getType();
            image = gson.fromJson(data.getString("Gson Bitmap"), type);
            // Blur the bitmap
            Bitmap output = WorkerUtils.blurBitmap(image, context);

            bitmapInterface = (BitmapInterface) context;
            bitmapInterface.getBitmap(output);

            // Write bitmap to a temp file
//            Uri outputUri = WorkerUtils.writeBitmapToFile(context, output);

            //displays notification
//            WorkerUtils.makeStatusNotification("Output is " + outputUri.toString(), context);

            // If there were no errors, return SUCCESS, with blurred image
            Data dataOut = new Data.Builder()
                    .putString("Gson out", gson.toJson(output))
                    .build();
            //****************************
            return Result.success(dataOut);
        } catch (Throwable throwable) {

            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}


