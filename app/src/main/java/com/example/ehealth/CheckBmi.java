package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class CheckBmi extends AppCompatActivity {

    private TextInputLayout Weight, Height;
    private Button CheckBmi,Continue;
    private TextView BmiDescription;
    private LinearLayout BmiLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bmi);

        Weight = findViewById(R.id.weight);
        Height = findViewById(R.id.height);
        CheckBmi = findViewById(R.id.checkBmi);
        Continue = findViewById(R.id.bmiContinue);
        BmiDescription = findViewById(R.id.bmiDescription);
        BmiLayout = findViewById(R.id.bmiLayout);


        CheckBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBmi();
            }
        });
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueToHome();
            }
        });

    }

    private void continueToHome() {
        Intent intent = new Intent(CheckBmi.this,Home.class);
        startActivity(intent);
    }

    private void checkBmi() {
        double getWeight = Double.parseDouble(Weight.getEditText().getText().toString());
        double getHeight = Double.parseDouble(Height.getEditText().getText().toString());
        double BMI = getWeight / (getHeight * getHeight);
        if (BMI<18.5){
            BmiLayout.setVisibility(View.VISIBLE);
            BmiDescription.setText("Your BMI is "+BMI+". This indicates that you are underweight and you need to add some " +
                    "weight. You wil be required to do short time fasting such as the 18/6 and 20/4 , check on your diet and " +
                    "do workouts to improve your muscle growth so you add weight.");
        }
    }
}