package com.example.ehealth;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class Results extends AppCompatActivity {
    public static String PREFS_HEIGHT = "myPref";
    Button Logout;
    private TextView Name, UpdatePassword, UpdateWeight;
    private TextInputLayout Email, Phone, Password, Weight, Height, Bmi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Name = findViewById(R.id.user_name);
        Email = findViewById(R.id.user_email);
        Phone = findViewById(R.id.user_phone);
        Password = findViewById(R.id.user_password);
        Weight = findViewById(R.id.user_weight);
        Height = findViewById(R.id.user_height);
        Bmi = findViewById(R.id.user_bmi);
        Logout = findViewById(R.id.user_logout);
        UpdatePassword = findViewById(R.id.update_my_password);
        UpdateWeight = findViewById(R.id.update_my_weight);

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
        String PHONE = sharedPreferences.getString("PhoneNumber", "");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(PHONE);
        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nameFromDatabase = snapshot.child(PHONE).child("name").getValue(String.class);
                    String emailFromDatabase = snapshot.child(PHONE).child("email").getValue(String.class);
                    String phoneFromDatabase = snapshot.child(PHONE).child("phone").getValue(String.class);
                    String passwordFromDatabase = snapshot.child(PHONE).child("password").getValue(String.class);
                    double weightFromDatabase = snapshot.child(PHONE).child("weight").getValue(double.class);
                    double heightFromDatabase = snapshot.child(PHONE).child("height").getValue(double.class);
                    double bmiFromDatabase = snapshot.child(PHONE).child("bmi").getValue(double.class);

                    Name.setText(nameFromDatabase);
                    Email.getEditText().setText(emailFromDatabase);
                    Phone.getEditText().setText(phoneFromDatabase);
                    Password.getEditText().setText(passwordFromDatabase);
                    Weight.getEditText().setText(new DecimalFormat("#.##").format(weightFromDatabase));
                    Height.getEditText().setText(new DecimalFormat("#.##").format(heightFromDatabase));
                    Bmi.getEditText().setText(new DecimalFormat("#.##").format(bmiFromDatabase));

                } else {
                    Name.setText("");
                    Email.getEditText().setText("");
                    Phone.getEditText().setText("");
                    Password.getEditText().setText("");
                    Weight.getEditText().setText("");
                    Height.getEditText().setText("");
                    Bmi.getEditText().setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Results.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setMessage("Are you sure you want to Logout?");
                builder.setTitle("Logout");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Results.this, Login.class);
                        startActivity(intent);
                        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn", false);
                        editor.commit();
                        finishAffinity();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        UpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = Height.getEditText().getText().toString();
                Intent intent;
                intent = new Intent(Results.this, Change_password.class);
                startActivity(intent);
                intent.putExtra("height", height);
                finish();
            }
        });

        UpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double height = Double.parseDouble(Height.getEditText().getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences(Results.PREFS_HEIGHT, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Height", String.valueOf(height));
                editor.apply();
                Intent intent;
                intent = new Intent(Results.this, Update_Weight.class);
                startActivity(intent);
            }
        });


    }

}