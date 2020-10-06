package com.nirmalya.governmentexams;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       /* FirebaseMessaging.getInstance().subscribeToTopic("COVID");*/
        splashTimer();
        //System.out.println("Jah");
    }

    public void splashTimer() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, NumberVerifActivity.class));
                finish();
            }
        }, 2000);


    }
}
