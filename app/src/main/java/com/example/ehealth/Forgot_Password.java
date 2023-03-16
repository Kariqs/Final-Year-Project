package com.example.ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Forgot_Password extends AppCompatActivity {

    private TextInputLayout Phone, Password, Confirm;
    private Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        Confirm = findViewById(R.id.confirmPassword);
        Reset = findViewById(R.id.reset);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhone() | !validatePassword() | !validateConfirm()) {
                    return;
                } else {
                    String phone = Phone.getEditText().getText().toString();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("users");
                    Query goToNumber = databaseReference.orderByChild("phone").equalTo(phone);
                    goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String newPassword = Password.getEditText().getText().toString();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(phone);
                                databaseReference.child("password").setValue(newPassword);
                                Toast.makeText(Forgot_Password.this, "Your password has been reset.", Toast.LENGTH_SHORT).show();
                                Intent intent;
                                intent = new Intent(Forgot_Password.this, Login.class);
                                startActivity(intent);
                                clear();
                                finish();
                            } else {
                                Phone.setError("Phone number does not exist.");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    public boolean validatePhone() {
        String value = Phone.getEditText().getText().toString();
        if (value.isEmpty()) {
            Phone.setError("Field cannot be empty");
            return false;
        } else if (value.length() > 15) {
            Phone.setError("Invalid phone number");
            return false;
        } else {
            Phone.setError(null);
            Phone.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validatePassword() {
        String value = Password.getEditText().getText().toString();
        if (value.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (value.length() < 6) {
            Password.setError("Weak password");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateConfirm() {
        String value = Confirm.getEditText().getText().toString();
        String value1 = Password.getEditText().getText().toString();
        if (value.isEmpty()) {
            Confirm.setError("Field cannot be empty");
            return false;
        } else if (!value.equals(value1)) {
            Confirm.setError("Passwords do not match.");
            return false;
        } else {
            Confirm.setError(null);
            Confirm.setErrorEnabled(false);
            return true;
        }
    }

    public void clear() {
        Phone.getEditText().setText("");
        Password.getEditText().setText("");
        Confirm.getEditText().setText("");
    }
}