package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Fast extends AppCompatActivity {
    ProgressBar Progress;
    TextView Countdown, Explain;
    Button StartEnd;
    EditText EnterHours;
    CardView SixteenEight, Omad, TwentyFour, Autophagy;

    CountDownTimer countDownTimer;
    boolean TimerRunning;

    long TimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);

        EnterHours = findViewById(R.id.enterHours);
        Explain = findViewById(R.id.explain);
        Countdown = findViewById(R.id.countDownTime);
        StartEnd = findViewById(R.id.startEnd);
        SixteenEight = findViewById(R.id.sixteenEight);
        Omad = findViewById(R.id.omad);
        TwentyFour = findViewById(R.id.twentyFour);
        Autophagy = findViewById(R.id.autophagy);

        StartEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Explain.setText("YOUR FAST HAS STARTED, GOOD LUCK.");
                countDownTimer.start();
                StartEnd.setText("END FAST");
            }
        });
        SixteenEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Countdown.setText("16:00:00");
                TimeLeftInMillis = 57600000;
                countDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
                    @Override
                    public void onTick(long l) {
                        TimeLeftInMillis = l;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        TimerRunning = false;
                        StartEnd.setVisibility(View.INVISIBLE);
                    }
                };
                TimerRunning = true;

                //StartEnd.setVisibility(View.VISIBLE);
            }
        });

    }

    public void updateCountDownText() {
        long seconds = TimeLeftInMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        minutes = minutes % 60;
        seconds = seconds % 60;

        String timeLeftFormated = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        Countdown.setText(timeLeftFormated);


    }

}