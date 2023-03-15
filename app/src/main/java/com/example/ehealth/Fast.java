package com.example.ehealth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Locale;

public class Fast extends AppCompatActivity {
    TextView Countdown, Explain;
    Button StartEnd;
    CardView SixteenEight, Omad, TwentyFour, Autophagy;
    CountDownTimer countDownTimer;
    boolean TimerRunning;
    long TimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);

        Explain = findViewById(R.id.explain);
        Countdown = findViewById(R.id.countDownTime);
        StartEnd = findViewById(R.id.startEnd);
        SixteenEight = findViewById(R.id.sixteenEight);
        Omad = findViewById(R.id.omad);
        TwentyFour = findViewById(R.id.twentyFour);
        Autophagy = findViewById(R.id.autophagy);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Break Fast", "Break Fast", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        StartEnd.setOnClickListener(view -> {
            String time = Countdown.getText().toString();
            if (!TimerRunning && !time.equals("00:00:00")) {
                Explain.setText("YOUR FAST HAS STARTED, GOOD LUCK.");
                countDownTimer.start();
                StartEnd.setText("END FAST");
            } else if (TimerRunning) {
                countDownTimer.cancel();
                Countdown.setText("00:00:00");
                StartEnd.setText("START FAST");
                Explain.setText("SELECT A FASTING PLAN AND CLICK START FAST TO CONTINUE WITH YOUR FAST.");
                TimerRunning = false;
            } else {
                Toast.makeText(this, "Please select a fasting plan to continue.", Toast.LENGTH_SHORT).show();
            }
        });
        SixteenEight.setOnClickListener(view -> {
            if (TimerRunning) {
                toastMessage();
            } else {
                Countdown.setText("16:00:00");
                TimeLeftInMillis = 3000;
                countDown();
            }
        });
        Omad.setOnClickListener(view -> {
            if (TimerRunning) {
                toastMessage();
            } else {
                Countdown.setText("23:00:00");
                TimeLeftInMillis = 82800000;
                countDown();
            }
        });
        Autophagy.setOnClickListener(view -> {
            if (TimerRunning) {
                toastMessage();
            } else {
                Countdown.setText("48:00:00");
                TimeLeftInMillis = 172800000;
                countDown();
            }
        });
        TwentyFour.setOnClickListener(view -> {
            if (TimerRunning) {
                toastMessage();
            } else {
                Countdown.setText("20:00:00");
                TimeLeftInMillis = 72000000;
                countDown();
            }
        });
    }

    private void toastMessage() {
        Toast.makeText(this, "Kindly end the ongoing fast to start another fast.", Toast.LENGTH_SHORT).show();
    }

    public void countDown() {
        countDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                TimeLeftInMillis = l;
                updateCountDownText();
                TimerRunning = true;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                notifyFastIsOver();
                StartEnd.setText("START FAST");
                Explain.setText("SELECT A FASTING PLAN AND CLICK START FAST TO CONTINUE WITH YOUR FAST.");
                TimerRunning = false;
            }
        };
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

    public void notifyFastIsOver() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "Break Fast");
        builder.setContentTitle("eHealth");
        builder.setContentText("CONGRATULATIONS! You have ended your fast.");
        builder.setSmallIcon(R.drawable.logo);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());
    }
}