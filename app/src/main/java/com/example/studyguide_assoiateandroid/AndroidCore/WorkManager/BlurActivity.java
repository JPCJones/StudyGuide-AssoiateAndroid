package com.example.studyguide_assoiateandroid.AndroidCore.WorkManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Data;
import androidx.work.WorkInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.studyguide_assoiateandroid.R;
import com.google.gson.Gson;

import java.io.IOException;

// workManager dependency
  //  implementation "androidx.work:work-runtime:2.5.0"

// tutorial - https://developer.android.com/codelabs/android-workmanager-java?authuser=1#10
// work manager info - https://developer.android.com/topic/libraries/architecture/workmanager/?authuser=1

public class BlurActivity extends AppCompatActivity implements BlurWork.BitmapInterface {
    private static final String TAG = "BlurActivity";

    public static final int IMAGE = 0;
    private Button picButton;
    private ImageView imageView;
    private Bitmap bitmapImage;

    private BlurViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);

        viewModel = ViewModelProviders.of(this).get(BlurViewModel.class);

        imageView = findViewById(R.id.capture_view);
        picButton = findViewById(R.id.button);

        onClickHandler();

        // Show work status, added in onCreate()
        viewModel.getOutputWorkInfo().observe(this, listOfWorkInfos -> {
            // If there are no matching work info, do nothing
            if (listOfWorkInfos == null || listOfWorkInfos.isEmpty()) {
                return;
            }
            // We only care about the first output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            WorkInfo workInfo = listOfWorkInfos.get(0);

            boolean finished = workInfo.getState().isFinished();
            if (!finished) {
                Log.d(TAG, "onCreate:not finished");
            } else {
                Log.d(TAG, "onCreate: finished");
                Data outputData = workInfo.getOutputData();
            }
        });
    }

    private void onClickHandler() {

        // take a picture *************
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, IMAGE);
            }
        });

        // pick from gallery *************
        findViewById(R.id.buttonGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void imageSelected() {
        findViewById(R.id.linear_options).setVisibility(View.VISIBLE);
        RadioGroup radioGroup = findViewById(R.id.radio_blur);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.little:
                        Toast.makeText(BlurActivity.this, "little", Toast.LENGTH_SHORT).show();
                        viewModel.applyBlur(1, bitmapImage,BlurActivity.this);
                        break;
                    case R.id.medium:
                        Toast.makeText(BlurActivity.this, "medium", Toast.LENGTH_SHORT).show();
                        viewModel.applyBlur(2, bitmapImage,BlurActivity.this);
                        break;
                    case R.id.most:
                        Toast.makeText(BlurActivity.this, "most", Toast.LENGTH_SHORT).show();
                        viewModel.applyBlur(3, bitmapImage, BlurActivity.this);
                        break;
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null){
            if(requestCode == 0){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
                bitmapImage = photo;
            }else if(requestCode == 1){
                try {
                    // URI to bitmap ***********
                    Uri imageUri = data.getData();
                    Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(photo);
                    bitmapImage = photo;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imageSelected();
        }
    }

    @Override
    public void getBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}