package com.nirmalya.irms.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.media.MediaBrowserServiceCompat;
import androidx.room.Database;

import com.google.zxing.Result;
import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.RoomDB.AppDatabase;
import com.nirmalya.irms.RoomDB.AppExecutors;
import com.nirmalya.irms.RoomDB.StudentModel;
import com.nirmalya.irms.databinding.DialogVerifyAttBinding;
import com.nirmalya.irms.model.response.CandidateAttendanceList;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;
import com.nirmalya.irms.utility.ZXingScannerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home_Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private static final int WRITE_EXST = 1;
    private static final int REQUEST_PERMISSION = 123;
    int CAMERA;
    String position, formt;
    ImageView imgFlashLight, imgBack;
    private boolean isFlashlightOn = false;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));

        context = this;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 5);
            }
        }

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);

        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        imgBack = findViewById(R.id.imgBack);
        imgFlashLight = findViewById(R.id.imgFlashLight);

        imgFlashLight.setOnClickListener(v -> {
            if (isFlashlightOn) {
                imgFlashLight.setImageResource(R.drawable.ic_lightning_deactivated);
                mScannerView.setFlash(false);
            } else {
                imgFlashLight.setImageResource(R.drawable.ic_lightning_activated);
                mScannerView.setFlash(true);
            }

            isFlashlightOn = !isFlashlightOn;
        });

        imgBack.setOnClickListener(v -> finish());
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        //Toast.makeText(this, "Contents = " + rawResult.getText() +", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_LONG).show();
        //position=rawResult.getText();
        String barcode = rawResult.toString();
        formt = rawResult.getBarcodeFormat().toString();
        new UpdateDatabase(barcode).execute();
    }

    @Override
    public void handleResult(MediaBrowserServiceCompat.Result rawResult) {
    }


    private class UpdateDatabase extends AsyncTask<String, String, StudentModel> {

        String barcode;
        AppDatabase db;

        public UpdateDatabase(String barcode) {
            this.barcode = barcode;
            db = AppDatabase.getInstance(getApplicationContext());
        }

        @SuppressLint("WrongConstant")
        @Override
        protected StudentModel doInBackground(String... strings) {
            return db.studentDao().selectData(barcode);

        }

        @Override
        protected void onPostExecute(StudentModel selectModel) {
            super.onPostExecute(selectModel);
            // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            /*Intent intent = new Intent();
            //intent.putExtra("Contents",position);
            intent.putExtra("Format", formt);
            setResult(RESULT_OK, intent);
            finish();*/

            if (selectModel != null) {
                String time = Utils.getCurrentTime();
                if (Osssc.getPrefs().getSelectEntryStatus()) {
                    int gateCount;
                    if (Osssc.getPrefs().getGateScanCount().equalsIgnoreCase("")) {
                        gateCount = 1;
                    } else {
                        gateCount = Integer.parseInt(Osssc.getPrefs().getGateScanCount()) + 1;
                    }
                    if (selectModel.getEntryStatus().equalsIgnoreCase("P")) {
                        //return "Barcode Already Scan";
                        MessageUtils.showFailureMessage(context, "Barcode Already Scan");
                        finishScanner();

                    } else {
                        Osssc.getPrefs().setGateScanCount(String.valueOf(gateCount));
                        String msg = selectModel.getStRollNo() + " Marked as present.";
                        showEntryAttendanceDialog(selectModel, msg, db);
                        //selectModel.setEntryStatus("P");
                        //selectModel.setEntryScanTime(time);
                        MessageUtils.showSuccessMessage(context, "Scan Successful");
                    }
                } else {
                    int hallCount;

                    if (Osssc.getPrefs().getHallScanCount().equalsIgnoreCase("")) {
                        hallCount = 1;
                    } else {
                        hallCount = Integer.parseInt(Osssc.getPrefs().getHallScanCount()) + 1;
                    }

                    if (selectModel.getHallStatus().equalsIgnoreCase("P") ||
                            selectModel.getHallStatus().equalsIgnoreCase("A")) {
                        MessageUtils.showFailureMessage(context, "Barcode Already Scan");
                        finishScanner();
                    } else {

                        if (selectModel.getEntryStatus().equalsIgnoreCase("P")) {
                            Osssc.getPrefs().setHallScanCount(String.valueOf(hallCount));
                            if (Osssc.getPrefs().getSelectHallAttendance()) {
                                String msg = selectModel.getStRollNo() + " Marked as present.";
                                showAttendanceDialog(selectModel, msg, true, db);
                                //selectModel.setHallScanTime(time);
                                //selectModel.setHallStatus("P");
                                MessageUtils.showSuccessMessage(context, "Scan Successful");
                            } else {
                                showDialog(selectModel,
                                        "Attendance status of the candidate marked as present in entry gate. Please re verify",
                                        false, db);
                            }
                        } else {
                            Osssc.getPrefs().setHallScanCount(String.valueOf(hallCount));
                            showDialog(selectModel,
                                    "Marked as absent in entry gate. Please re verify.",
                                    true, db);
                            //return "This candidate attendance marked as absent in entry gate. Please verify..";
                        }
                    }
                }
                AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.studentDao().updateResource(selectModel);
                    }
                });

            } else {
                MessageUtils.showFailureMessage(context, "Invalid Barcode");
                finishScanner();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showDialog(StudentModel selectModel, String message, boolean attendanceType, AppDatabase db) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        DialogVerifyAttBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_verify_att, null, false);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        dialogBinding.titleDialog.setText("Verify It");

        String strMessage = selectModel.getStRollNo() + " " + message;

        dialogBinding.txtDetails.setText(strMessage);

        dialogBinding.btnCancel.setOnClickListener(v -> {
            alertDialog.dismiss();
            finishScanner();
        });

        dialogBinding.btnIAgree.setOnClickListener(v -> {
            String msg = "";
            if (attendanceType) {
                msg = "Still want to be " + selectModel.getStRollNo() + " to be Present.";
            } else {
                msg = "Still want to be " + selectModel.getStRollNo() + " to be Absent.";
            }
            showAttendanceDialog(selectModel, msg, attendanceType, db);
            alertDialog.dismiss();
        });

        alertDialog.setView(dialogBinding.getRoot());
        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void showAttendanceDialog(StudentModel selectModel, String message, boolean attType, AppDatabase db) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        DialogVerifyAttBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_verify_att, null, false);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        dialogBinding.titleDialog.setText("Attendance");

        dialogBinding.txtDetails.setText(message);

        dialogBinding.btnCancel.setOnClickListener(v -> {
            alertDialog.dismiss();
            finishScanner();
        });

        dialogBinding.btnIAgree.setOnClickListener(v -> {
            if (attType) {
                selectModel.setHallStatus("P");
                MessageUtils.showSuccessMessage(context, selectModel.getStRollNo() + " Set as Present.");
            } else {
                selectModel.setHallStatus("A");
                MessageUtils.showSuccessMessage(context, selectModel.getStRollNo() + " Set as Absent.");
            }
            selectModel.setHallScanTime(Utils.getCurrentTime());
            AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
                @Override
                public void run() {
                    db.studentDao().updateResource(selectModel);
                    finishScanner();
                }
            });
        });

        alertDialog.setView(dialogBinding.getRoot());
        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void showEntryAttendanceDialog(StudentModel selectModel, String message, AppDatabase db) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        DialogVerifyAttBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_verify_att, null, false);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        dialogBinding.titleDialog.setText("Attendance");

        dialogBinding.txtDetails.setText(message);

        dialogBinding.btnCancel.setOnClickListener(v -> {
            alertDialog.dismiss();
            finishScanner();
        });

        dialogBinding.btnIAgree.setOnClickListener(v -> {
            selectModel.setEntryStatus("P");
            selectModel.setEntryScanTime(Utils.getCurrentTime());
            MessageUtils.showSuccessMessage(context, selectModel.getStRollNo() + " Set as Present.");

            AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
                @Override
                public void run() {
                    db.studentDao().updateResource(selectModel);
                    finishScanner();
                }
            });
        });

        alertDialog.setView(dialogBinding.getRoot());
        alertDialog.show();
    }

    private void finishScanner() {
        Intent intent = new Intent();
        //intent.putExtra("Contents",position);
        intent.putExtra("Format", formt);
        setResult(RESULT_OK, intent);
        finish();
    }
}
