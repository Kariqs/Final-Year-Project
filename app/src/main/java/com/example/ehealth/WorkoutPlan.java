package com.example.ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlan extends AppCompatActivity {
    TextView GoBack;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        GoBack = findViewById(R.id.goBack);
        recyclerView = findViewById(R.id.recyclerView);

        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutPlan.this, Workout.class);
                startActivity(intent);
                finish();
            }
        });
    }
}