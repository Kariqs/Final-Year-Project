package com.example.ehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {
     private Button Upload;
     private RecyclerView recyclerView;
     private ResultsAdapter resultsAdapter;
     private List<Upload> uploadList;
     private DatabaseReference databaseReference;
     private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Upload = findViewById(R.id.uploadResult);
        recyclerView = findViewById(R.id.recyclerView);

        //set layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        uploadList = new ArrayList<>();
         // fetch image from firebase

        databaseReference = FirebaseDatabase.getInstance().getReference();

        getDataFromFirebase();

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Results.this,UploadResults.class);
                startActivity(intent);
            }
        });
    }

    private void getDataFromFirebase() {
        Query query = databaseReference.child("uploads");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                 Upload upload = new Upload();

                 upload.setDescription(dataSnapshot.child("description").getValue().toString());
                 upload.setImageUrl(dataSnapshot.child("imageUrl").getValue().toString());

                 uploadList.add(upload);
             }
             resultsAdapter = new ResultsAdapter(getApplicationContext(),uploadList);
             recyclerView.setAdapter(resultsAdapter);
             resultsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}