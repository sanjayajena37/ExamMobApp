package com.nirmalya.irms.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.utility.MessageUtils;
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
        requestPhonePermission();
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

    private void requestPhonePermission() {
        Dexter.withActivity(SplashScreen.this)
                .withPermission(Manifest.permission.READ_PHONE_STATE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        if (Utils.isNetworkAvailable(context, true)) {
                            startSplashing();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            MessageUtils.showFailureMessage(context, R.string.str_phone_permission_denied_msg);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
