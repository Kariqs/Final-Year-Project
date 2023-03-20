package com.example.ehealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
            boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);

            Intent intent;
            if (hasLoggedIn) {
                intent = new Intent(Splash.this, Home.class);
            } else {
                intent = new Intent(Splash.this, Login.class);
            }
            startActivity(intent);
            finish();
        }, SPLASH_TIME);
    }
}