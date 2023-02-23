package com.example.ehealth;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {
    TextView WorkoutPlan;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        WorkoutPlan = findViewById(R.id.workoutPlan);
        recyclerView = findViewById(R.id.recyclerView);
        List<WorkoutModel> workOutList = new ArrayList<WorkoutModel>();
        workOutList.add(new WorkoutModel("THE DEAD LIFT","The dead lift is a movement in which your hips hinge backward to lower down and pick up a weighted barbell or kettle-bell from the floor. Your back is flat throughout the movement. Some benefits of performing dead lifts include strengthening and gaining more definition in your upper and lower back, glutes, and hamstrings.",R.drawable.deadlift));
        workOutList.add(new WorkoutModel("SQUAT","A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up. During the descent, the hip and knee joints flex while the ankle joint dorsiflexions; conversely the hip and knee joints extend and the ankle joint plantar flexes when standing up.",R.drawable.deadlift));
        workOutList.add(new WorkoutModel("THE BENCH PRESS","The bench press is a compound exercise that targets the muscles of the upper body. It involves lying on a bench and pressing weight upward using either a barbell or a pair of dumbbells. During a bench press, you lower the weight down to chest level and then press upwards while extending your arms.",R.drawable.deadlift));
        workOutList.add(new WorkoutModel("THE OVERHEAD PRESS","The overhead press is an upper-body weight training exercise in which the trainee presses a weight overhead while seated or standing. It is mainly used to develop the anterior deltoid muscles of the shoulder.",R.drawable.deadlift));
        workOutList.add(new WorkoutModel("THE LUNGE","Stand with your feet hip-width apart. Step forward and bend both knees, lowering until your knees are bent at a 90-degree angle. Shift forward onto the lead leg. Push off on both legs and step through, lifting your back leg and bringing it forward so your rear foot lands ahead of you in a lunge position.",R.drawable.deadlift));
        workOutList.add(new WorkoutModel("THE BULGARIAN SPLIT SQUAT","A Bulgarian split squat is essentially a single-leg squat. Your back leg is elevated behind you on a steady box, chair, or bench, and your weight is loaded onto your front leg. It's a unilateral move that targets your quads, hamstrings, glutes, and calves.",R.drawable.deadlift));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new WorkoutAdapter(getApplicationContext(),workOutList));
    }





}