package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {

    TextInputLayout Email;
    Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Email = findViewById(R.id.update_email);
        Update = findViewById(R.id.update_details);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedEmail = Email.getEditText().getText().toString().trim();
                if (!TextUtils.isEmpty(updatedEmail)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME,0);
                    String Phone = sharedPreferences.getString("PhoneNumber","");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Phone).child("email");
                    databaseReference.setValue(updatedEmail);
                    Toast.makeText(Update.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Update.this, "No data to update.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}