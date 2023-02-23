package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

import java.net.URI;

public class UploadResults extends AppCompatActivity {
     final int PICK_IMAGE_REQUEST = 1;
    private Button pickImage,uploadImage;
    private TextInputLayout Description;
    private ImageView Display;
    private ProgressBar uploadProgress;

    private URI uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_results);
        pickImage = findViewById(R.id.pickImage);
        uploadImage = findViewById(R.id.post);
        Description = findViewById(R.id.caption);
        Display = findViewById(R.id.display);
        uploadProgress = findViewById(R.id.uploadProgress);
    }
}