package com.example.studyguide_assoiateandroid.AndroidCore;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studyguide_assoiateandroid.R;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static com.example.studyguide_assoiateandroid.AndroidCore.NotificationEx.CHANNEL_ID;

// make sure to add to manifest
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: started");
       int type = intent.getIntExtra("id", -1);
        if( type == 0){
            Log.d(TAG, "onReceive: in here");
        }
        else if(type == 1){
            String response = String.valueOf(getMessageText(intent));
            Log.d(TAG, "onReceive: "+ response);
            getMessageText(intent);

            /*
            You should also append the reply to the bottom of the notification by calling setRemoteInputHistory().
             However, if you’re building a messaging app, you should create a messaging-style notification and append the new message to the conversation.
             */

            // Build a new notification, which informs the user that the system
// handled their interaction with the previous notification.
            Notification repliedNotification = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_menu_manage)
                    .setContentText("getString(R.string.replied)")
                    .build();

// Issue the new notification.
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager .notify(5, repliedNotification);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}
