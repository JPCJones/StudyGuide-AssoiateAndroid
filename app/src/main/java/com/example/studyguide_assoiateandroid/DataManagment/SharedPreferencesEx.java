package com.example.studyguide_assoiateandroid.DataManagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studyguide_assoiateandroid.R;

public class SharedPreferencesEx extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    private String sharedPrefName = "om.example.studyguide_assoiateandroid.DataManagment";

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
// *************** use onSaveInstanceState to save values if using Bundle savedInstanceState object *************
        textView = findViewById(R.id.textView3);

        //******** get shared preferences ***********
        sharedPreferences = getSharedPreferences(sharedPrefName, MODE_PRIVATE);

            textView.setText(sharedPreferences.getString("Count", "0"));

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.valueOf(textView.getText().toString()) + 1;
                textView.setText(String.valueOf(value));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Count", textView.getText().toString());
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}