package com.example.ehealth;

import static java.time.LocalTime.now;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;

public class Home extends AppCompatActivity {
    TextView Greetings;
CardView Fasting,Training,Dieting,Results;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Greetings = findViewById(R.id.greetings);
        Fasting = findViewById(R.id.fast);
        Training = findViewById(R.id.workout);
        Dieting = findViewById(R.id.diet);
        Results = findViewById(R.id.results);

       // String Phone = getIntent().getStringExtra("phone");

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME,0);
        String Phone = sharedPreferences.getString("PhoneNumber","");


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(Phone);
        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String Name  = snapshot.child(Phone).child("name").getValue(String.class);

                    if (now().isBefore(LocalTime.of(12,00))){
                        Greetings.setText("Good Morning "+Name+", Welcome.");
                    }else if (now().isAfter(LocalTime.of(12,00)) && now().isBefore(LocalTime.of(16,00))){
                        Greetings.setText("Good Afternoon "+Name+", Welcome");
                    }else if (now().isAfter(LocalTime.of(16,00))){
                        Greetings.setText("Good Evening "+Name+", Welcome.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        Fasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Fast.class);
                startActivity(i);
            }
        });
        Training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Workout.class);
                startActivity(i);
            }
        });
        Dieting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Diet.class);
                i.putExtra("Phone",Phone);
                startActivity(i);
            }
        });
        Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, com.example.ehealth.Results.class);
                i.putExtra("Phone",Phone);
                startActivity(i);
            }
        });

    }
}