package com.nirmalya.governmentexams;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.Collections;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanQrCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    private static final String TAG = "Scan Result";
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);
        scannerView = findViewById(R.id.scannerView);
        scannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(scannerView);
    }
    @Override
        public void onResume() {
            super.onResume();
        scannerView.setResultHandler((ZXingScannerView.ResultHandler) this); // Register ourselves as a handler for scan results.
        scannerView.startCamera();          // Start camera on resume
        }

        @Override
        public void onPause() {
            super.onPause();
            scannerView.stopCamera();           // Stop camera on pause
        }

    @Override
    public void handleResult(Result result) {
        Log.e(TAG, result.getText());
        Log.e(TAG, result.getBarcodeFormat().toString());

        Toast.makeText(this,result.getText(),Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (scannerView != null) {
            scannerView.setResultHandler(this);
            scannerView.setFormats(Collections.singletonList(BarcodeFormat.QR_CODE));
            scannerView.setAutoFocus(true);
            scannerView.setAspectTolerance(0.5f);
            scannerView.startCamera();
            scannerView.setFlash(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.setFlash(false);
        scannerView.stopCamera();
    }
}
