package com.nirmalya.irms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nirmalya.irms.network.Preferences;
import com.nirmalya.irms.utility.AppSignatureHashHelper;
import com.nirmalya.irms.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Osssc extends Application {

    public static final String TAG = Osssc.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static Osssc osssc;
    private static Preferences preferences;
    private RequestQueue mRequestQueue;

    private Locale locale = null;

    public Osssc() {
    }

    public static synchronized Osssc getInstance() {
        if (osssc == null) {
            osssc = new Osssc();
        }
        return osssc;
    }

    public static Preferences getPrefs() {
        return preferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        osssc = this;
        preferences = new Preferences(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NotNull Activity activity, Bundle savedInstanceState) {
                if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }

            @Override
            public void onActivityStarted(@NotNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NotNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NotNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NotNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NotNull Activity activity) {

            }
        });

        AppSignatureHashHelper appSignatureHelper = new AppSignatureHashHelper(this);
        appSignatureHelper.getAppSignatures();

        Configuration config = getBaseContext().getResources().getConfiguration();
        String lang = getBaseContext().getResources().getString(R.string.pref_locale);

        if (!(Utils.isNullOrEmpty(lang) && config.locale.getLanguage().equals(lang))) {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (locale != null) {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
