package com.example.ehealth;

import static java.time.LocalTime.now;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalTime;

public class Home extends AppCompatActivity {
    TextView Greetings;
CardView Fasting,Training,Dieting,Results;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Greetings = findViewById(R.id.greetings);
        Fasting = findViewById(R.id.fast);
        Training = findViewById(R.id.workout);
        Dieting = findViewById(R.id.diet);
        Results = findViewById(R.id.results);

        if (now().isBefore(LocalTime.of(12,00))){
            Greetings.setText("Good Morning, Welcome.");
        }else if (now().isAfter(LocalTime.of(12,00)) && now().isBefore(LocalTime.of(16,00))){
            Greetings.setText("Good Afternoon, Welcome");
        }else if (now().isAfter(LocalTime.of(16,00))){
            Greetings.setText("Good Evening, Welcome.");
        }

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