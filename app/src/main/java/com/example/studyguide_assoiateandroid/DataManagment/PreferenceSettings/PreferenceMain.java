package com.example.studyguide_assoiateandroid.DataManagment.PreferenceSettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.studyguide_assoiateandroid.DataManagment.SharedPreferencesEx;
import com.example.studyguide_assoiateandroid.R;


// tutorial - https://developer.android.com/codelabs/android-training-adding-settings-to-app#3
public class PreferenceMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_main);

        //set defaults for the settings - last argument tells the method whether to rest the settings each time or not
        PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);

        TextView textView = findViewById(R.id.setting_text);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String text = sharedPreferences.getString("signature", "none");
        textView.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.drawer_layout_ex, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.setting_text);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String text = sharedPreferences.getString("signature", "none");
        textView.setText(text);
    }
}