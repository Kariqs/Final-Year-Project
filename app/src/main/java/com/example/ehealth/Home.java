package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
ImageView Fasting,Training,Dieting,Results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fasting = findViewById(R.id.fast);
        Training = findViewById(R.id.workout);
        Dieting = findViewById(R.id.diet);
        Results = findViewById(R.id.results);

        Fasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Fast.class);
                startActivity(i);
            }
        });
        Training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Workout.class);
                startActivity(i);
            }
        });
        Dieting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Diet.class);
                startActivity(i);
            }
        });
        Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, com.example.ehealth.Results.class);
                startActivity(i);
            }
        });

    }
}