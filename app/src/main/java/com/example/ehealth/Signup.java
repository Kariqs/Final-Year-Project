package com.example.ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Signup extends AppCompatActivity {
    private TextInputLayout Name, Email, PhoneNumber, Gender, Password, ConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.signUpName);
        Email = findViewById(R.id.signUpEmail);
        PhoneNumber = findViewById(R.id.signUpPhoneNo);
        Password = findViewById(R.id.signUpPassword);
        Gender = findViewById(R.id.signUpGender);
        ConfirmPassword = findViewById(R.id.signUpConfirmPassword);
        Button go = findViewById(R.id.signUpButton);
        TextView alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);


        go.setOnClickListener(view -> {
            if (!validateEmail() | !validatePhoneNumber() | !validatePassword() | !validateName() | !validateGender() | !validateConfirmPassword()) {
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

    private boolean validateConfirmPassword() {
        String value = Password.getEditText().getText().toString();
        String value1 = ConfirmPassword.getEditText().getText().toString();
        if (!value.equals(value1)) {
            ConfirmPassword.setError("Password do not match.");
            return false;
        } else {
            ConfirmPassword.setError(null);
            ConfirmPassword.setErrorEnabled(false);
            return true;
        }
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
        } else if (value.length()<10 | value.length() > 15) {
            PhoneNumber.setError("Invalid phone number.");
            return false;
        } else {
            PhoneNumber.setError(null);
            PhoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateGender() {
        String value = Objects.requireNonNull(Gender.getEditText()).getText().toString();
        if (value.isEmpty()) {
            Gender.setError("Field cannot be empty");
            return false;
        } else if (!value.equals("MALE") && !value.equals("FEMALE")) {
            Gender.setError("Input gender in upper case or invalid gender.");
            return false;
        } else {
            Gender.setError(null);
            Gender.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = Objects.requireNonNull(Password.getEditText()).getText().toString();
        if (value.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (value.length() < 6) {
            Password.setError("Weak password.");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    private void registerUser() {
        String phone = PhoneNumber.getEditText().getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");
        Query checkNumber = databaseReference.orderByChild("phone").equalTo(phone);
        checkNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    PhoneNumber.setError("Phone number already registered.");
                } else {
                    String NAME = Objects.requireNonNull(Name.getEditText()).getText().toString();
                    String EMAIL = Objects.requireNonNull(Email.getEditText()).getText().toString();
                    String PHONENUMBER = Objects.requireNonNull(PhoneNumber.getEditText()).getText().toString();
                    String GENDER = Gender.getEditText().getText().toString();
                    String PASSWORD = Objects.requireNonNull(Password.getEditText()).getText().toString();

                    Name.getEditText().setText("");
                    Email.getEditText().setText("");
                    PhoneNumber.getEditText().setText("");
                    Gender.getEditText().setText("");
                    Password.getEditText().setText("");
                    ConfirmPassword.getEditText().setText("");
                    Intent i = new Intent(Signup.this, CheckBmi.class);
                    i.putExtra("keyname", NAME);
                    i.putExtra("keyemail", EMAIL);
                    i.putExtra("keyphone", PHONENUMBER);
                    i.putExtra("keygender", GENDER);
                    i.putExtra("keypassword", PASSWORD);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}