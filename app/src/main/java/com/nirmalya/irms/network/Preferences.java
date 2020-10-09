package com.nirmalya.irms.network;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Preferences(Context context) {
        String preferenceName = "Osssc";
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public void clearPreference() {
        preferences.edit().clear().apply();
    }

    public Boolean getIsLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        editor = preferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }
}
