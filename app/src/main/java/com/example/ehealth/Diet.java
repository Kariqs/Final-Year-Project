package com.example.ehealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Diet extends AppCompatActivity {

    private TextInputLayout Age, Activity;
    private Button Calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        Age = findViewById(R.id.age);
        Activity = findViewById(R.id.activity);
        Calculate = findViewById(R.id.calculate);


        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAge() | !validateActivity()) {
                    return;
                } else {
                    calculateCaloriesNeeded();
                }
            }
        });


    }

    public void calculateCaloriesNeeded() {
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
        String Phone = sharedPreferences.getString("PhoneNumber", "");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(Phone);

        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int inputAge = Integer.parseInt(Age.getEditText().getText().toString());
                    double inputHeight = snapshot.child(Phone).child("height").getValue(double.class);
                    double heightInCm = inputHeight * 100;
                    double inputWeight = snapshot.child(Phone).child("weight").getValue(double.class);
                    String inputGender = snapshot.child(Phone).child("gender").getValue(String.class);
                    double inputActivityLevel = Double.parseDouble(Activity.getEditText().getText().toString());

                    int maleCaloriesNeeded = (int) ((88.362 + (13.397 * inputWeight) + (4.799 * heightInCm) - (5.677 * inputAge)) * inputActivityLevel);
                    int femaleCaloriesNeeded = (int) ((447.593 + (9.247 * inputWeight) + (3.098 * heightInCm) - (4.330 * inputAge)) * inputActivityLevel);

                    if (inputGender.equals("MALE")) {
                        String maleCalories = Integer.toString(maleCaloriesNeeded);
                        Intent intent = new Intent(Diet.this, DietHome.class);
                        intent.putExtra("calories", maleCalories);
                        startActivity(intent);
                        finish();
                    } else {
                        String femaleCalories = Integer.toString(femaleCaloriesNeeded);
                        Intent intent = new Intent(Diet.this, DietHome.class);
                        intent.putExtra("calories", femaleCalories);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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


