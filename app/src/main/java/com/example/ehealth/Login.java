package com.example.ehealth;

import static java.time.LocalTime.now;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;

public class Login extends AppCompatActivity {
    public static String PREFS_NAME = "myPref";
    TextView Greetings;
    TextInputLayout Email, Password;
    Button Go;
    TextView dontHaveAccount;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Greetings = findViewById(R.id.greetings);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Go = findViewById(R.id.login);
        dontHaveAccount = findViewById(R.id.dontHaveAccount);

        if (now().isBefore(LocalTime.of(12, 00))) {
            Greetings.setText("Good Morning, Login to continue.");
        } else if (now().isAfter(LocalTime.of(12, 00)) && now().isBefore(LocalTime.of(16, 00))) {
            Greetings.setText("Good Afternoon, Login to continue");
        } else if (now().isAfter(LocalTime.of(16, 00))) {
            Greetings.setText("Good Evening, Login to continue.");
        }

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                } else {
                    validateUser();
                }
            }
        });


        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });
    }

    private void clear() {
        Email.getEditText().setText("");
        Password.getEditText().setText("");
    }

    private boolean validateEmail() {
        String val = Email.getEditText().getText().toString();
        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = Password.getEditText().getText().toString();
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    private void validateUser() {


        String enteredEmail = Email.getEditText().getText().toString();
        String enteredPassword = Password.getEditText().getText().toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = databaseReference.orderByChild("phone").equalTo(enteredEmail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    Email.setError(null);
                    Email.setErrorEnabled(false);

                    String passwordFromDatabase = snapshot.child(enteredEmail).child("password").getValue(String.class);
                    if (passwordFromDatabase.equals(enteredPassword)) {

                        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("PhoneNumber", enteredEmail);
                        editor.apply();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.commit();


                        Intent intent = new Intent(Login.this, Home.class);
                        intent.putExtra("phone", enteredEmail);
                        startActivity(intent);
                        finish();
                        clear();
                    } else {
                        Password.setError("wrong password.");
                        Password.requestFocus();
                    }
                } else {
                    Email.setError("The phone number doesn't exist.");
                    Email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}




