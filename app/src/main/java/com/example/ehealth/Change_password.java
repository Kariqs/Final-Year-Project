package com.example.ehealth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
                String Password = OldPassword.getEditText().getText().toString().trim();
                String updatedPassword = NewPassword.getEditText().getText().toString().trim();
                if (Password.equals(NewPassword)) {
                    Toast.makeText(Change_password.this, "New password cannot be as old password.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!TextUtils.isEmpty(Password) && !TextUtils.isEmpty(updatedPassword)) {
                        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
                        String Phone = sharedPreferences.getString("PhoneNumber", "");
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Phone);
                        databaseReference.child("password").setValue(updatedPassword);
                        Toast.makeText(Change_password.this, "Password Changed.", Toast.LENGTH_SHORT).show();

                        Clear();

                    } else {
                        Toast.makeText(Change_password.this, "No data to update.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void Clear() {
        OldPassword.getEditText().setText("");
        NewPassword.getEditText().setText("");
    }
}
