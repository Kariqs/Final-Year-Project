package com.example.ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckBmi extends AppCompatActivity {

    private TextInputLayout Weight, Height;
    private Button CheckBmi, Continue;
    private TextView BmiDescription, BmiGreet;
    private LinearLayout CheckLayout, BmiLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bmi);

        Weight = findViewById(R.id.weight);
        Height = findViewById(R.id.height);
        CheckBmi = findViewById(R.id.checkBmi);
        Continue = findViewById(R.id.bmiContinue);
        BmiGreet = findViewById(R.id.greet);
        BmiDescription = findViewById(R.id.bmiDescription);
        BmiLayout = findViewById(R.id.bmiLayout);
        CheckLayout = findViewById(R.id.checkLayout);

        String NAME = getIntent().getStringExtra("keyname");
        BmiGreet.setText("HELLO " + NAME + ", LET'S CHECK YOUR BMI");
        CheckBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateWeight() | !validateHeight()) {
                    return;
                } else {
                    checkBmi();
                }
            }
        });

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                finish();
            }
        });

    }

    private boolean validateWeight() {
        String val = Weight.getEditText().getText().toString();
        if (val.isEmpty()) {
            Weight.setError("Field cannot be empty.");
            return false;
        }
        double weight = Double.parseDouble(val);
        if (weight <= 0 || weight > 200) {
            Weight.setError("Invalid weight.");
            return false;
        } else {
            Weight.setError(null);
            Weight.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateHeight() {
        String val = Height.getEditText().getText().toString();
        if (val.isEmpty()) {
            Height.setError("Field cannot be empty.");
            return false;
        }
        double height = Double.parseDouble(val);
        if (height <= 0 || height > 2.5) {
            Height.setError("Invalid height.");
            return false;
        } else {
            Height.setError(null);
            Height.setErrorEnabled(false);
            return true;
        }
    }


    private void checkBmi() {
        double getWeight = Double.parseDouble(Weight.getEditText().getText().toString());
        double getHeight = Double.parseDouble(Height.getEditText().getText().toString());
        double BMI = getWeight / (getHeight * getHeight);
        if (BMI < 18.5) {
            CheckLayout.setVisibility(View.INVISIBLE);
            BmiLayout.setVisibility(View.VISIBLE);
            BmiDescription.setText("Your BMI is " + BMI + ". This indicates that you are underweight and you need to add some " +
                    "weight. You wil be required to do short time fasting such as the 18/6 and 20/4 , check on your diet and " +
                    "do workouts to improve your muscle growth so you add weight.");
        } else if (BMI > 18.5 && BMI < 25) {
            CheckLayout.setVisibility(View.INVISIBLE);
            BmiLayout.setVisibility(View.VISIBLE);
            BmiDescription.setText("Your BMI is " + BMI + ". This indicates that you have normal weight." +
                    "You wil be required to do short time fasting such as the 18/6 and 20/4 and " +
                    "do workouts to maintain your body weight.");
        } else if (BMI > 25 && BMI < 30) {
            CheckLayout.setVisibility(View.INVISIBLE);
            BmiLayout.setVisibility(View.VISIBLE);
            BmiDescription.setText("Your BMI is " + BMI + ". This indicates that you are overweight and you need to add some " +
                    "weight. You wil be required to do long time fasting such as the OMAD and 48 Hours fasting to burn fats" +
                    " ,check on your diet to ensure you do not eat foods that can add your weight, " +
                    "do workouts to enable you shed weight.");
        } else {
            CheckLayout.setVisibility(View.INVISIBLE);
            BmiLayout.setVisibility(View.VISIBLE);
            BmiDescription.setText("Your BMI is " + BMI + ". This indicates that you are obese and you need to lose a reasonable " +
                    "amount of weight. You wil be required to do long time fasting such as OMAD and 48hrs , check on your diet and " +
                    "do workouts to burn fats so that you can go back to normal weight.");
        }

    }

    private void saveData() {
        double getWeight = Double.parseDouble(Weight.getEditText().getText().toString());
        double getHeight = Double.parseDouble(Height.getEditText().getText().toString());
        double getBMI = getWeight / (getHeight * getHeight);

        String NAME = getIntent().getStringExtra("keyname");
        String EMAIL = getIntent().getStringExtra("keyemail");
        String PHONE = getIntent().getStringExtra("keyphone");
        String GENDER = getIntent().getStringExtra("keygender");
        String PASSWORD = getIntent().getStringExtra("keypassword");
        String WEIGHT = Double.toString(getWeight);
        String HEIGHT = Double.toString(getHeight);
        String BMI = Double.toString(getBMI);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        userHelper userhelper = new userHelper(NAME, EMAIL, PHONE, GENDER, PASSWORD, WEIGHT, HEIGHT, BMI);
        databaseReference.child(PHONE).setValue(userhelper);
        Toast.makeText(this, "Details saved successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CheckBmi.this, Login.class);
        startActivity(intent);
        finish();
    }


}
