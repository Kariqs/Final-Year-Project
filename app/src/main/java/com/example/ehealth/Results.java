package com.example.ehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class Results extends AppCompatActivity {

    private TextView Name,Logout;
    private TextInputLayout Email,Phone,Password,Weight,Height,Bmi;
    private Button Update;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Name = findViewById(R.id.user_name);
        Email = findViewById(R.id.user_email);
        Phone = findViewById(R.id.user_phone);
        Password = findViewById(R.id.user_password);
        Weight = findViewById(R.id.user_weight);
        Height =  findViewById(R.id.user_height);
        Bmi = findViewById(R.id.user_bmi);
        Logout = findViewById(R.id.user_logout);
        Update = findViewById(R.id.update_details);

       // String PHONE = getIntent().getStringExtra("Phone");
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME,0);
        String PHONE = sharedPreferences.getString("PhoneNumber","");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(PHONE);
        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
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

                }else{
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
                Toast.makeText((Context) Results.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(Results.this,Login.class);
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent;
            intent= new Intent(Results.this, com.example.ehealth.Update.class);
            startActivity(intent);
            finish();
            }
        });



    }

}