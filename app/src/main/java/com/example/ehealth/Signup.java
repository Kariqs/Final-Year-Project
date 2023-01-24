package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
TextInputLayout Email,PhoneNumber,Password;
Button Go;
TextView AlreadyHaveAccount;

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Email = findViewById(R.id.signUpEmail);
        PhoneNumber = findViewById(R.id.signUpPhoneNo);
        Password = findViewById(R.id.signUpPassword);
        Go = findViewById(R.id.signUpButton);
        AlreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EMAIL = Email.getEditText().getText().toString();
                String PHONENUMBER = PhoneNumber.getEditText().getText().toString();
                String PASSWORD = Password.getEditText().getText().toString();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("users");

                userHelperClass userhelper = new userHelperClass(EMAIL,PHONENUMBER,PASSWORD);

                databaseReference.child(PHONENUMBER).setValue(userhelper);

                Toast.makeText(Signup.this, "SignUp was successful.", Toast.LENGTH_SHORT).show();
                Email.getEditText().setText("");
                PhoneNumber.getEditText().setText("");
                Password.getEditText().setText("");
                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
                finish();
            }
        });


        AlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}