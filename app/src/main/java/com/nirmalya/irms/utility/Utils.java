package com.nirmalya.irms.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.nirmalya.irms.R;
import com.nirmalya.irms.activity.DashbordActivity;
import com.nirmalya.irms.activity.LoginActivity;
import com.nirmalya.irms.activity.NumberVerifActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Utils {

    public static boolean isNetworkAvailable(Context context, Boolean showNetworkToast) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()
                && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            if (showNetworkToast) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() ->
                        showNoInternetAvailable(context.getApplicationContext()));
            }
            return false;
        }
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog Dialog = new ProgressDialog(mContext);
        try {
            Dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
        Dialog.setCancelable(false);
        Objects.requireNonNull(Dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Dialog.setContentView(R.layout.progressbar);
        return Dialog;
    }

    /*No Internet Message*/
    public static void showNoInternetAvailable(Context context) {
        Toast.makeText(context, "No Internet Available!!", Toast.LENGTH_LONG).show();
    }

    public static void startHomeActivity(Context context) {
        try {
            Intent intent = new Intent(context, DashbordActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startRootActivity(Context context) {
        try {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty() || value.equals("null");
    }

    public static void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = ((Activity) context).getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(context);
        }

        view.clearFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getDeviceIMEI(Context context) {

        String deviceId = null;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);

                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                /*if (telephonyManager != null && telephonyManager.getDeviceId() != null) {
                    deviceId = telephonyManager.getDeviceId();
                } else {
                    deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceId;
    }

    public static HashMap<String, String> getTokenHeaderMap(String token) {

        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + token);
        return params;
    }

    public static String getCurrentTime() {
        Date today = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

        return format.format(today);
    }
}
