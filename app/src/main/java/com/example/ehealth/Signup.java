package com.example.ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signup extends AppCompatActivity {
    private TextInputLayout Name,Email, PhoneNumber, Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.signUpName);
        Email = findViewById(R.id.signUpEmail);
        PhoneNumber = findViewById(R.id.signUpPhoneNo);
        Password = findViewById(R.id.signUpPassword);
        Button go = findViewById(R.id.signUpButton);
        TextView alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);


        go.setOnClickListener(view -> {
            if (!validateEmail() | !validatePhoneNumber() | !validatePassword() | !validateName()) {
                return;
            } else {
                registerUser();
            }
        });


        alreadyHaveAccount.setOnClickListener(view -> {
            Intent i = new Intent(Signup.this, Login.class);
            startActivity(i);
            finish();
        });

    }

    private boolean validateEmail() {
        String value = Objects.requireNonNull(Email.getEditText()).getText().toString();
        if (value.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateName() {
        String value = Objects.requireNonNull(Name.getEditText()).getText().toString();
        if (value.isEmpty()) {
            Name.setError("Field cannot be empty");
            return false;
        } else {
            Name.setError(null);
            Name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        String value = Objects.requireNonNull(PhoneNumber.getEditText()).getText().toString();
        if (value.isEmpty()) {
            PhoneNumber.setError("Field cannot be empty");
            return false;
        } else {
            PhoneNumber.setError(null);
            PhoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = Objects.requireNonNull(Password.getEditText()).getText().toString();
        if (value.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    private void registerUser() {
        String NAME = Objects.requireNonNull(Name.getEditText()).getText().toString();
        String EMAIL = Objects.requireNonNull(Email.getEditText()).getText().toString();
        String PHONENUMBER = Objects.requireNonNull(PhoneNumber.getEditText()).getText().toString();
        String PASSWORD = Objects.requireNonNull(Password.getEditText()).getText().toString();

        Email.getEditText().setText("");
        PhoneNumber.getEditText().setText("");
        Password.getEditText().setText("");
        Intent i = new Intent(Signup.this, CheckBmi.class);
        i.putExtra("keyname",NAME);
        i.putExtra("keyemail",EMAIL);
        i.putExtra("keyphone",PHONENUMBER);
        i.putExtra("keypassword",PASSWORD);
        startActivity(i);
        finish();
    }

}