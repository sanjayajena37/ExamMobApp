package com.nirmalya.governmentexams.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

import com.nirmalya.governmentexams.R;

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
}
