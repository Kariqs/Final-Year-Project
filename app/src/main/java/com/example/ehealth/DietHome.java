package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DietHome extends AppCompatActivity {

    private TextView Explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_home);

        Explanation = findViewById(R.id.dietExplanation);
        int CALORIES_NEEDED = Integer.parseInt(getIntent().getStringExtra("calories"));

        Explanation.setText("You need "+CALORIES_NEEDED+" calories per day.");
    }




}