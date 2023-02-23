package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

public class UploadResults extends AppCompatActivity {

    Button pickImage,uploadImage;
    TextInputLayout Description;
    ImageView Display;
    ProgressBar uploadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_results);
    }
}