package com.nirmalya.irms.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private Context context;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;
        startSplashing();
        //System.out.println("Jah");
    }

    private void startSplashing() {
        handler = new Handler();
        runnable = this::checkLogin;
        handler.postDelayed(runnable, 2000);
    }

    // After Splash Screen move to Next Screen Root Activity
    private void checkLogin() {
        boolean isLog = Osssc.getPrefs().getIsLoggedIn();
        if (isLog) {
            Utils.startHomeActivity(context);
        } else {
            // If Not Logged In
            Utils.startRootActivity(context);
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
