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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.room.Database;

import com.google.zxing.Result;
import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.RoomDB.AppDatabase;
import com.nirmalya.irms.RoomDB.StudentModel;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;
import com.nirmalya.irms.utility.ZXingScannerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    private class UpdateDatabase extends AsyncTask<String, String, String> {

        String barcode;
        AppDatabase db;

        public UpdateDatabase(String barcode) {
            this.barcode = barcode;
            db = AppDatabase.getInstance(getApplicationContext());
        }

        @SuppressLint("WrongConstant")
        @Override
        protected String doInBackground(String... strings) {
            StudentModel selectModel = db.studentDao().selectData(barcode);
            if (selectModel != null) {
                String time = Utils.getCurrentTime();
                if (Osssc.getPrefs().getSelectEntryStatus()) {
                    int gateCount;
                    if(Osssc.getPrefs().getGateScanCount().equalsIgnoreCase("")) {
                        gateCount = 1;
                    } else {
                        gateCount = Integer.parseInt(Osssc.getPrefs().getGateScanCount()) + 1;
                    }
                    Osssc.getPrefs().setGateScanCount(String.valueOf(gateCount));
                    selectModel.setEntryStatus("P");
                    selectModel.setEntryScanTime(time);
                } else {
                    int hallCount;
                    if(Osssc.getPrefs().getHallScanCount().equalsIgnoreCase("")) {
                        hallCount = 1;
                    } else {
                        hallCount = Integer.parseInt(Osssc.getPrefs().getHallScanCount()) + 1;
                    }
                    Osssc.getPrefs().setHallScanCount(String.valueOf(hallCount));
                    if (Osssc.getPrefs().getSelectHallAttendance()) {
                        selectModel.setHallStatus("P");
                    } else {
                        selectModel.setHallStatus("A");
                    }
                    selectModel.setHallScanTime(time);
                }
                db.studentDao().updateResource(selectModel);
                return "Scan Successful";
            } else {
                return "Invalid Barcode";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            //intent.putExtra("Contents",position);
            intent.putExtra("Format", formt);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
