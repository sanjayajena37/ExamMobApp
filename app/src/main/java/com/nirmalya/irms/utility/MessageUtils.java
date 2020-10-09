package com.nirmalya.irms.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nirmalya.irms.R;


public class MessageUtils {

    private static final String COLOR_BLACK = "#000000";
    private static final String COLOR_SUCCESS = "#2B9C46";
    private static final String COLOR_FAIL = "#D90000";
    private static final String COLOR_WARNING = "#FFA909";

    private static final int TYPE_INTERNET = 0;
    private static final int TYPE_SUCCESS = 1; // Status Display Type TOAST
    private static final int TYPE_WARNING = 2; // Status Display Type ERROR
    private static final int TYPE_FAIL = 3;

    /*Success Message*/
    public static void showSuccessMessage(Context context, int message) {
        showSuccessMessage(context, context.getResources().getString(message));
    }

    public static void showSuccessMessage(Context context, String message) {
        showCustomToast(context, TYPE_SUCCESS, message);
    }

    /*Failure Message*/
    public static void showFailureMessage(Context context, int message) {
        showFailureMessage(context, context.getResources().getString(message));
    }

    public static void showFailureMessage(Context context, String message) {
        showCustomToast(context, TYPE_FAIL, message);
    }

    /*No Internet Message*/
    public static void showNoInternetAvailable(Context context) {
        showCustomToast(context, TYPE_INTERNET, context.getResources().getString(R.string.txt_msg_no_internet_available));
    }

    /*Please try again Message*/
    public static void showPleaseTryAgain(Context context) {
        showCustomToast(context, TYPE_FAIL, context.getResources().getString(R.string.txt_msg_please_try_again));
    }

    @SuppressLint("InflateParams")
    private static void showCustomToast(Context context, int type, String message) {

        View layout = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast, null);

        TextView txt_toast_msg = layout.findViewById(R.id.txt_toast_msg);
        txt_toast_msg.setText(message);

        switch (type) {
            case TYPE_INTERNET:
                txt_toast_msg.setTextColor(Color.parseColor(COLOR_BLACK));
                txt_toast_msg.setCompoundDrawablePadding((int) context.getResources().getDimension(R.dimen._5sdp));
                txt_toast_msg.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher_round, 0, 0, 0);
                break;
            case TYPE_SUCCESS:
                txt_toast_msg.setTextColor(Color.parseColor(COLOR_SUCCESS));
                break;
            case TYPE_FAIL:
                txt_toast_msg.setTextColor(Color.parseColor(COLOR_FAIL));
                break;
            case TYPE_WARNING:
                txt_toast_msg.setTextColor(Color.parseColor(COLOR_WARNING));
                break;
        }

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void showTostOnTop(Context context, int msg) {
        Toast toastPer = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toastPer.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toastPer.show();
    }
}