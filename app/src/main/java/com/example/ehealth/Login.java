package com.example.ehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputLayout Email, Password;
    Button Go;
    TextView dontHaveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Go = findViewById(R.id.login);
        dontHaveAccount = findViewById(R.id.dontHaveAccount);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()){
                    return;
                }else{
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

    private void validateUser(){
        String enteredEmail = Email.getEditText().getText().toString();
        String enteredPassword = Password.getEditText().getText().toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = databaseReference.orderByChild("email").equalTo(enteredEmail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    Email.setError(null);
                    Email.setErrorEnabled(false);

                    String passwordFromDatabase=snapshot.child(enteredEmail).child("password").getValue(String.class);
                    if (passwordFromDatabase.equals(enteredPassword)){
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);
                    }else{
                        Password.setError("wrong password.");
                        Password.requestFocus();
                    }
                }else{
                    Email.setError("The email doesn't exist.");
                    Email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}




