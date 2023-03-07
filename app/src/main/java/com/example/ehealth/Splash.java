package com.example.ehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
  private static final int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME,0);
            boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);

            Intent intent;
            if (hasLoggedIn){
                intent = new Intent(Splash.this, Home.class);
            }else{
                intent = new Intent(Splash.this, Login.class);
            }
            startActivity(intent);
            finish();
        },SPLASH_TIME);
    }
}