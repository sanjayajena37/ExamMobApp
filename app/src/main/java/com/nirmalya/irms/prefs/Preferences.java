package com.nirmalya.irms.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nirmalya.irms.model.response.ScannerData;
import com.nirmalya.irms.utility.Constant;

public class Preferences {

    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_SCANNER_MOBILE = "scanner_mobile";
    private static final String KEY_SCANNER_DATA_JSON = "scanner_request_json";

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

    public String getScannerMobile() {
        return preferences.getString(KEY_SCANNER_MOBILE, Constant.STRING_EMPTY);
    }

    public void setScannerMobile(String scannerMobile) {
        editor = preferences.edit();
        editor.putString(KEY_SCANNER_MOBILE, scannerMobile);
        editor.apply();
    }

    public ScannerData getScannerData() {
        String userScannerDataJsonAsString = preferences.getString(KEY_SCANNER_DATA_JSON, Constant.STRING_EMPTY);
        return !userScannerDataJsonAsString.equalsIgnoreCase(Constant.STRING_EMPTY) ?
                (ScannerData) new Gson().fromJson(userScannerDataJsonAsString, new TypeToken<ScannerData>() {
                }.getType()) : new ScannerData();
    }

    public void setScannerData(ScannerData scannerData) {
        editor = preferences.edit();
        editor.putString(KEY_SCANNER_DATA_JSON, new Gson().toJson(scannerData));
        editor.apply();
    }
}
