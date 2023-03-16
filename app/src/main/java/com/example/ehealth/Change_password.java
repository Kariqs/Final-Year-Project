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

public class Change_password extends AppCompatActivity {

    TextInputLayout OldPassword, NewPassword;
    Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        OldPassword = findViewById(R.id.update_email);
        NewPassword = findViewById(R.id.update_password);
        Update = findViewById(R.id.update_details);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword()) {
                    return;
                } else {
                    String updatedPassword = NewPassword.getEditText().getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
                    String Phone = sharedPreferences.getString("PhoneNumber", "");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Phone);
                    databaseReference.child("password").setValue(updatedPassword);
                    Toast.makeText(Change_password.this, "Password Changed.", Toast.LENGTH_SHORT).show();
                    Clear();
                }
            }

        });

    }

    private boolean validatePassword() {
        String val = OldPassword.getEditText().getText().toString();
        String val1 = NewPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            OldPassword.setError("Field cannot be empty.");
            return false;
        } else if (val1.isEmpty()) {
            NewPassword.setError("Field cannot be empty.");
            return false;
        } else if (val1.length() < 6) {
            NewPassword.setError("Weak password");
            return false;
        } else if (val.equals(val1)) {
            NewPassword.setError("New password cannot be as old password");
            return false;
        } else {
            OldPassword.setError(null);
            NewPassword.setError(null);
            OldPassword.setErrorEnabled(false);
            NewPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void Clear() {
        OldPassword.getEditText().setText("");
        NewPassword.getEditText().setText("");
    }
}
