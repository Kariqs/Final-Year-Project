package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Diet extends AppCompatActivity {

    private TextInputLayout Age,Height,Weight,Gender,Activity;
    private Button Calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        Age = findViewById(R.id.age);
        Height = findViewById(R.id.height);
        Weight = findViewById(R.id.weight);
        Gender = findViewById(R.id.gender);
        Activity = findViewById(R.id.activity);
        Calculate= findViewById(R.id.calculate);




        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAge()|!validateWeight()|!validateHeight()|!validateGender()|!validateActivity()){
                    return;
                }else {
                    calculateCaloriesNeeded();
                }
            }
        });


    }

    public void calculateCaloriesNeeded(){
        //Take inputs.
        String PHONE = getIntent().getStringExtra("Phone");
        Toast.makeText(this,PHONE, Toast.LENGTH_SHORT).show();
        int inputAge = Integer.parseInt(Age.getEditText().getText().toString());
        double inputHeight = Double.parseDouble(Height.getEditText().getText().toString());
        double inputWeight = Double.parseDouble(Weight.getEditText().getText().toString());
        String inputGender = Gender.getEditText().getText().toString();
        double inputActivityLevel = Double.parseDouble(Activity.getEditText().getText().toString());

        //calculate daily calories need.
        int maleCaloriesNeeded = (int) ((88.362 + (13.397*inputWeight) + (4.799*inputHeight) - (5.677*inputAge))*inputActivityLevel);
        int femaleCaloriesNeeded = (int) ((447.593 + (9.247*inputWeight) + (3.098*inputHeight) - (4.330*inputAge))*inputActivityLevel);

        //Toast daily calories needed

        if (inputGender.equals("MALE")){
            String maleCalories =Integer.toString(maleCaloriesNeeded);
            Intent intent = new Intent(Diet.this, DietHome.class);
            intent.putExtra("calories",maleCalories);
            //intent.putExtra("phone",PHONE);
            startActivity(intent);
        }else{
            String femaleCalories = Integer.toString(femaleCaloriesNeeded);
            Intent intent = new Intent(Diet.this,DietHome.class);
            intent.putExtra("calories",femaleCalories);
            //intent.putExtra("phone",PHONE);
            startActivity(intent);
        }
    }
    private boolean validateAge() {
        String val = Age.getEditText().getText().toString();
        if (val.isEmpty()) {
            Age.setError("Field cannot be empty");
            return false;
        } else {
            Age.setError(null);
            Age.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateWeight() {
        String val = Weight.getEditText().getText().toString();
        if (val.isEmpty()) {
            Weight.setError("Field cannot be empty");
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
            Height.setError("Field cannot be empty");
            return false;
        } else {
            Height.setError(null);
            Height.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateGender() {
        String val = Gender.getEditText().getText().toString();
        if (val.isEmpty()) {
            Gender.setError("Field cannot be empty");
            return false;
        }
        else{
            Gender.setError(null);
            Gender.setErrorEnabled(false);
            return true;
        }}
    private boolean validateActivity() {
        String val = Activity.getEditText().getText().toString();
        if (val.isEmpty()) {
            Activity.setError("Field cannot be empty");
            return false;
        } else {
            Activity.setError(null);
            Activity.setErrorEnabled(false);
            return true;
        }
    }
}


