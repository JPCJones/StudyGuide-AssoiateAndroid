package com.example.studyguide_assoiateandroid.Timber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studyguide_assoiateandroid.R;

import timber.log.Timber;

public class TimberTest extends AppCompatActivity {
    //**********************************************************************
    //must make class that extends application
    // must register class in manifest using tag - name/ EX: android:name=".Timber.ClickerApplication"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber_test);

       Button button = findViewById(R.id.log_button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Timber.d("this is a timber log");
           }
       });
    }
}