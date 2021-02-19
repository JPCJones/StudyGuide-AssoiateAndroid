package com.example.studyguide_assoiateandroid.AndroidCore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.example.studyguide_assoiateandroid.R;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class NotificationEx extends AppCompatActivity {
    private static final String TAG = "NotificationEx";
    // expandable notification styles - https://developer.android.com/training/notify-user/expanded
    // create notification with media controls - https://developer.android.com/training/notify-user/expanded?authuser=1#media-style
    public static final String CHANNEL_ID = "test channel";

    private  NotificationManagerCompat notificationManager;
    private int notificationId;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_ex);

        // get notification manager
        notificationManager = NotificationManagerCompat.from(this);

        //create the notification channel
        createNotificationChannel();

        sendBasicNotification();
        
        notificationWithActionButton();

        noifyWithTextResponce();

        notifyProgress();

        notifyFullScreenIntent();

        //  NotificationCompat.MessagingStyle for messenger notifications
    }

    private void notifyFullScreenIntent() {
        // add permission to manifest- <uses-permission android:name="android.permission.USE_FULL_SCREEN_INTENT"/>
        Intent fullScreenIntent = new Intent(this, Notification.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true);

        findViewById(R.id.notify_fullscreen_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(4, builder.build());
            }
        });
    }

    private void notifyProgress() {
        // consider using downloadManager for downloads- https://developer.android.com/reference/android/app/DownloadManager

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_LOW);

// Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());

// Do the job here that tracks the progress.
// Usually, this should be in a
// worker thread
// To show progress, update PROGRESS_CURRENT and update the notification with:
// builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
// notificationManager.notify(notificationId, builder.build());

        final int[] progress = {0};
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(100);
                progress[0] += 10;
                builder.setProgress(PROGRESS_MAX, progress[0], false);
            }
        }).start();
// When done, update the notification one more time to remove the progress bar
        builder.setContentText("Download complete")
                .setProgress(0,0,false);
        notificationManager.notify(notificationId, builder.build());
    }

    //*************************
    // Key for the string that's delivered in the action's intent
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void noifyWithTextResponce() {


        String replyLabel = "reply label";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("id", 1);
        // Build a PendingIntent for the reply action to trigger.
        PendingIntent replyPendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),3,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input.
        Notification.Action action =
                new Notification.Action.Builder(R.drawable.ic_launcher_background,
                        "getString(R.string.label)", replyPendingIntent)
                        // add the input method
                        .addRemoteInput(remoteInput)
                        .build();

        // Build the notification and add the action.
        Notification newMessageNotification = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("getString(R.string.title)")
                .setContentText("getString(R.string.content)")
                .addAction(action)
                .build();

        // Issue the notification.
        findViewById(R.id.notify_respond_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationId = 5;
                notificationManager.notify(notificationId, newMessageNotification);
            }
        });

    }
    //*************************************************************************************************************
    private void notificationWithActionButton() {
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction("toast");
        snoozeIntent.putExtra("id", 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_launcher_foreground, "send toast message", snoozePendingIntent)
                // set what the message is
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        findViewById(R.id.notify_action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationId = 2;
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(notificationId, builder.build());
            }
        });
    }

    private void sendBasicNotification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationEx.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // this style allows for more text
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        findViewById(R.id.notify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationId = 1;
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(notificationId, builder.build());
            }
        });
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name object";
            String description = "description object";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}