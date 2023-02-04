package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class Fast extends AppCompatActivity {

    int duration;
    TextView Countdown;
    Button StartEnd;
    CardView SixteenEight, Omad, TwentyFour, Autophagy;

    CountDownTimer countDownTimer;
    boolean TimerRunning;

    int TimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);
        Countdown = findViewById(R.id.countDownTime);
        StartEnd = findViewById(R.id.startEnd);
        SixteenEight = findViewById(R.id.sixteenEight);
        Omad = findViewById(R.id.omad);
        TwentyFour = findViewById(R.id.twentyFour);
        Autophagy = findViewById(R.id.autophagy);

        StartEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SixteenEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            duration = 5760000;
            TimeLeftInMillis = duration;
            updateCountDownText();
            }
        });
    }
    public void updateCountDownText(){
        int hours = (int) (TimeLeftInMillis/1000)/360;
        int minutes = (int)(TimeLeftInMillis/1000)%60;
        int seconds = (int)(TimeLeftInMillis/1000)%60;

        String timeLeftFormated = String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,seconds);
        Countdown.setText(timeLeftFormated);


    }
}