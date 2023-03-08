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
                //Take inputs.
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
                    startActivity(intent);
                }else{
                    String femaleCalories = Integer.toString(femaleCaloriesNeeded);
                    Intent intent = new Intent(Diet.this,DietHome.class);
                    intent.putExtra("calories",femaleCalories);
                    startActivity(intent);
                }
            }
        });


    }
}