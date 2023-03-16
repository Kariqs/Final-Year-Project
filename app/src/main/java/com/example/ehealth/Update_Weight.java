package com.example.ehealth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_Weight extends AppCompatActivity {
    private Button Save;
    private TextInputLayout NewWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_weight);

        NewWeight = findViewById(R.id.new_weight);
        Save = findViewById(R.id.save_new_weight);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateWeight()) {
                    return;
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences(Results.PREFS_HEIGHT, 0);
                    double Height = Double.parseDouble(sharedPreferences.getString("Height", ""));
                    SharedPreferences sharedPreference = getSharedPreferences(Login.PREFS_NAME, 0);
                    String PHONE = sharedPreference.getString("PhoneNumber", "");
                    double myNewWeight = Double.parseDouble(NewWeight.getEditText().getText().toString());
                    double newBmi = myNewWeight / (Height * Height);
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("users").child(PHONE);
                    databaseReference.child("weight").setValue(myNewWeight);
                    databaseReference.child("bmi").setValue(newBmi);
                    Toast.makeText(Update_Weight.this, "Updated Successfully.", Toast.LENGTH_SHORT).show();
                    NewWeight.getEditText().setText("");
                }
            }
        });
    }

    private boolean validateWeight() {
        String val = NewWeight.getEditText().getText().toString();
        if (val.isEmpty()) {
            NewWeight.setError("Field cannot be empty.");
            return false;
        }
        double weight = Double.parseDouble(val);
        if (weight <= 0 || weight > 200) {
            NewWeight.setError("Invalid weight.");
            return false;
        } else {
            NewWeight.setError(null);
            NewWeight.setErrorEnabled(false);
            return true;
        }
    }

}