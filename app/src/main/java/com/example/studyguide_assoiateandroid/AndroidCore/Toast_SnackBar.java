package com.example.studyguide_assoiateandroid.AndroidCore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyguide_assoiateandroid.R;
import com.google.android.material.snackbar.Snackbar;

public class Toast_SnackBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_ex);

        Toast toast = Toast.makeText(this, "this is a toast", Toast.LENGTH_SHORT);
        //positioning
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);


        // standard toast
        findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();
            }
        });

        // Custom toast
        findViewById(R.id.custom_toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast_layout,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("This is a custom toast");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, -200);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

        // **************** Snack Bar **********************
        //Snackbars work best if they are displayed inside of a CoordinatorLayout.
        // CoordinatorLayout allows the snackbar to enable behavior like swipe-to-dismiss,
        //as well as automatically moving widgets like FloatingActionButton.

        findViewById(R.id.SnackBar_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view the snack bar will be displayed in
                View view = findViewById(R.id.shrine_container);
                Snackbar.make(view, "snack bar", Snackbar.LENGTH_SHORT)
                        // set action buttons
                        .setAction("Yes", new View.OnClickListener() {@Override public void onClick(View v) { }})
                        // position snackbar above certain view in container
                        .setAnchorView(findViewById(R.id.custom_toast_button))
                        .show();
            }
        });
    }
}