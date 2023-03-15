package com.example.ehealth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WorkoutPlans extends AppCompatActivity {

    private TextView Workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans);

        Workout = findViewById(R.id.workout_plan);

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
        String Phone = sharedPreferences.getString("PhoneNumber", "");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(Phone);

        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    double bmiFromDatabase = snapshot.child(Phone).child("bmi").getValue(double.class);
                    if (bmiFromDatabase < 18.5) {

                        Workout.setText("You need workouts  to grow your muscle so that you can add weight." + "\n" +
                                "You will have to train three times a week as scheduled below." + "\n" +
                                "Warm Up:Pull ups and Push ups." + "\n" +
                                "Monday: Squats,Bench Press,Bent Over Rows." + "\n" +
                                "Wednesday:Deadlift, Lunges and Overhead press." + "\n" +
                                "Friday: Deadlift, Bench Press and Bulgarian split squats." +
                                "For every exercise you will perform 3 sets with 5 reps each.");
                    } else if (bmiFromDatabase > 18.5 && bmiFromDatabase < 25) {
                        Workout.setText("You need workouts to maintain your muscle mass." + "\n" +
                                "You will have to train three times a week as scheduled below." + "\n" +
                                "Warm Up:Pull ups and Push ups." + "\n" +
                                "Monday: Squats,Bench Press,Bent Over Rows." + "\n" +
                                "Wednesday:Deadlift, Lunges and Overhead press" + "\n" +
                                "Friday: Squats, Bench Press and Bulgarian split squats." +
                                "For every exercise you will perform 3 sets with 7 reps each.");
                    } else if (bmiFromDatabase > 25 && bmiFromDatabase < 30) {
                        Workout.setText("You need to train for strength. To loose weight just check on your diet." + "\n" +
                                "You will have to train three times a week as scheduled below." + "\n" +
                                "Monday: Run 10 kilometres." + "\n" +
                                "Wednesday:Deadlift, Lunges and Overhead press" + "\n" +
                                "Friday: Walk atleast 7000 steps." +
                                "For every exercise you will perform 3 sets with 6 reps each.");
                    } else {
                        Workout.setText("You need to train for strength. To loose weight just check on your diet." + "\n" +
                                "You will have to train three times a week as scheduled below." + "\n" +
                                "Monday: Run 12 kilometres." + "\n" +
                                "Wednesday:Squats, Lunges and Overhead press" + "\n" +
                                "Friday: Walk atleast 9000 steps." +
                                "For every exercise you will perform 3 sets with 6 reps each.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}