package com.nirmalya.irms.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nirmalya.irms.model.response.CandidateAttendanceList;
import com.nirmalya.irms.model.response.ScannerData;
import com.nirmalya.irms.utility.Constant;

import java.util.List;

public class Preferences {

    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_SCANNER_MOBILE = "scanner_mobile";
    private static final String KEY_SCANNER_DATA_JSON = "scanner_request_json";
    private static final String KEY_EXAM_DATE_TIME = "exam_date_time";
    private static final String KEY_EXAM_SHIFT = "exam_shift";
    private static final String KEY_ENTRY_STATUS = "entry_status";
    private static final String KEY_ATTENDANCE = "hall_attendance";
    private static final String KEY_HALL_COUNT = "hall_scan_count";
    private static final String KEY_GATE_COUNT = "gate_scan_count";
    private static final String KEY_CANDIDATE_ATTENDANCE = "candidate_attendance";

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

    public String getExamDateTime() {
        return preferences.getString(KEY_EXAM_DATE_TIME, Constant.STRING_EMPTY);
    }

    public void setExamDateTime(String examDateTime) {
        editor = preferences.edit();
        editor.putString(KEY_EXAM_DATE_TIME, examDateTime);
        editor.apply();
    }

    public String getExamShift() {
        return preferences.getString(KEY_EXAM_SHIFT, Constant.STRING_EMPTY);
    }

    public void setExamShift(String examShift) {
        editor = preferences.edit();
        editor.putString(KEY_EXAM_SHIFT, examShift);
        editor.apply();
    }

    public boolean getSelectEntryStatus() {
        return preferences.getBoolean(KEY_ENTRY_STATUS, true);
    }

    public void setSelectEntryStatus(boolean status) {
        editor = preferences.edit();
        editor.putBoolean(KEY_ENTRY_STATUS, status);
        editor.apply();
    }

    public boolean getSelectHallAttendance() {
        return preferences.getBoolean(KEY_ATTENDANCE, false);
    }

    public void setSelectHallAttendance(boolean attendance) {
        editor = preferences.edit();
        editor.putBoolean(KEY_ATTENDANCE, attendance);
        editor.apply();
    }

    public String getHallScanCount() {
        return preferences.getString(KEY_HALL_COUNT, Constant.STRING_EMPTY);
    }

    public void setHallScanCount(String count) {
        editor = preferences.edit();
        editor.putString(KEY_HALL_COUNT, count);
        editor.apply();
    }

    public String getGateScanCount() {
        return preferences.getString(KEY_GATE_COUNT, Constant.STRING_EMPTY);
    }

    public void setGateScanCount(String count) {
        editor = preferences.edit();
        editor.putString(KEY_GATE_COUNT, count);
        editor.apply();
    }

    public ScannerData getCandidateAttendanceData() {
        String userScannerDataJsonAsString = preferences.getString(KEY_CANDIDATE_ATTENDANCE, Constant.STRING_EMPTY);
        return !userScannerDataJsonAsString.equalsIgnoreCase(Constant.STRING_EMPTY) ?
                (ScannerData) new Gson().fromJson(userScannerDataJsonAsString, new TypeToken<ScannerData>() {
                }.getType()) : new ScannerData();
    }

   /* public void setCandidateAttendanceData(CandidateAttendanceList candidateAttendanceList) {
        editor = preferences.edit();
        editor.putString(KEY_CANDIDATE_ATTENDANCE, new Gson().toJson(candidateAttendanceList));
        editor.apply();
    }*/

    public void setAttendanceListData(List<CandidateAttendanceList> candidateAttendanceList) {
        editor = preferences.edit();
        editor.putString(KEY_CANDIDATE_ATTENDANCE, new Gson().toJson(candidateAttendanceList));
        editor.apply();
    }
}
