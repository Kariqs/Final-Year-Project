package com.example.ehealth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadResults extends AppCompatActivity {
     final int PICK_IMAGE_REQUEST = 1;
    private Button pickImage,uploadImage;
    private TextInputLayout Description;
    private ImageView Display;
    private ProgressBar uploadProgress;

    private Uri uri;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private StorageTask storageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_results);
        pickImage = findViewById(R.id.pickImage);
        uploadImage = findViewById(R.id.post);
        Description = findViewById(R.id.caption);
        Display = findViewById(R.id.display);
        uploadProgress = findViewById(R.id.uploadProgress);

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storageTask != null && storageTask.isInProgress()){
                    Toast.makeText(UploadResults.this, "Upload in progress.", Toast.LENGTH_SHORT).show();
                }else{
                    uploadFile();
                }
            }
        });

    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadFile() {
     if (uri != null){
         StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
         storageTask = fileReference.putFile(uri)
                 .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         Handler handler = new Handler();
                         handler.postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 uploadProgress.setProgress(0);
                             }
                         },3000);
                         Toast.makeText(UploadResults.this, "Upload was successful.", Toast.LENGTH_SHORT).show();
                         Upload upload = new Upload(Description.getEditText().getText().toString().trim(),
                                 fileReference.getDownloadUrl().toString());
                         String uploadId = databaseReference.push().getKey();
                         databaseReference.child(uploadId).setValue(upload);
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(UploadResults.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 })
                 .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                      double progress = (100.0 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                      uploadProgress.setProgress((int) progress);
                     }
                 });
     }else{
         Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
     }
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