package com.example.studyguide_assoiateandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordListDisplayActivity;
import com.example.studyguide_assoiateandroid.AndroidCore.NotificationEx;
import com.example.studyguide_assoiateandroid.AndroidCore.Toast_SnackBar;
import com.example.studyguide_assoiateandroid.AndroidCore.WorkManager.BlurActivity;
import com.example.studyguide_assoiateandroid.DataManagment.PreferenceSettings.PreferenceMain;
import com.example.studyguide_assoiateandroid.DataManagment.PreferenceSettings.SettingsActivity;
import com.example.studyguide_assoiateandroid.DataManagment.SharedPreferencesEx;
import com.example.studyguide_assoiateandroid.Timber.TimberTest;
import com.example.studyguide_assoiateandroid.UserInterface.CustomDrawable_Menus;
import com.example.studyguide_assoiateandroid.UserInterface.DrawerLayoutEx;
import com.example.studyguide_assoiateandroid.UserInterface.MaterialComponentSHRINE.ShrineMain;
import com.example.studyguide_assoiateandroid.UserInterface.NavigationLayoutTry2;
import com.example.studyguide_assoiateandroid.UserInterface.PagingEx.PagingExample;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.LinearLayout);
        initButtons();
    }

    private void initButtons() {
        // directory for all lessons - https://developers.google.com/certification/associate-android-developer/study-guide
        // ************** android core *****************
        createButton("Toast/SnackBar - core", Toast_SnackBar.class);
        createButton("Notification Ex", NotificationEx.class);
        createButton("Android Architecture", WordListDisplayActivity.class);
        createButton("WorkManager /  Picture Intents, implicit intents", BlurActivity.class);

        // ******************** User Interface *****************************
        createButton("Navigation Drawer", DrawerLayoutEx.class);
        createButton("My navigation activity", NavigationLayoutTry2.class);
        createButton("Paging", PagingExample.class);
        createButton("Custom Drawable", CustomDrawable_Menus.class);
        createButton("Material Components - Shrine", ShrineMain.class);
        createButton("Timbre log", TimberTest.class);
        // ********************** Data Management ************************
        createButton("shared preferences", SharedPreferencesEx.class);
        createButton("Setting Preferences", PreferenceMain.class);

    }

    private void createButton(String name, Class<?> activity) {
        Button button = new Button(this);
        button.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity);
                startActivity(intent);
            }
        });
        linearLayout.addView(button);
    }
}