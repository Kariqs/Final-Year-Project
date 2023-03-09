package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputLayout;


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

        String NAME = getIntent().getStringExtra("mname");
        String EMAIL = getIntent().getStringExtra("memail");
        String PHONE = getIntent().getStringExtra("mphone");
        String PASSWORD = getIntent().getStringExtra("mpassword");
        String WEIGHT = getIntent().getStringExtra("mweight");
        String HEIGHT = getIntent().getStringExtra("mheight");
        String BMI = getIntent().getStringExtra("mbmi");

        Name.setText(NAME);
        Email.getEditText().setText(EMAIL);
        Phone.getEditText().setText(PHONE);
        Password.getEditText().setText(PASSWORD);
        Weight.getEditText().setText(WEIGHT);
        Height.getEditText().setText(HEIGHT);
        Bmi.getEditText().setText(BMI);

    }


}