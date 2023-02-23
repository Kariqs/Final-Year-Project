package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class Results extends AppCompatActivity {
      Button Upload;
      RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Upload = findViewById(R.id.uploadResult);
        recyclerView = findViewById(R.id.recyclerView);
    }
}