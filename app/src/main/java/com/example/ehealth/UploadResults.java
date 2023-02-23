package com.example.ehealth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class UploadResults extends AppCompatActivity {
     final int PICK_IMAGE_REQUEST = 1;
    private Button pickImage,uploadImage;
    private TextInputLayout Description;
    private ImageView Display;
    private ProgressBar uploadProgress;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_results);
        pickImage = findViewById(R.id.pickImage);
        uploadImage = findViewById(R.id.post);
        Description = findViewById(R.id.caption);
        Display = findViewById(R.id.display);
        uploadProgress = findViewById(R.id.uploadProgress);

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            uri = data.getData();
            Picasso.get().load(uri).into(Display);
        }
    }
}