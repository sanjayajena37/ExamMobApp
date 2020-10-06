package com.nirmalya.governmentexams;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.nirmalya.governmentexams.databinding.ActivityScanQrCodeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

import androidx.databinding.DataBindingUtil;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanQrCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    private static final String TAG = ScanQrCodeActivity.class.getSimpleName();
    private ActivityScanQrCodeBinding binding;
    private Context context;
    private boolean isFlashlightOn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_qr_code);

        context = this;

        initView();
    }

    private void initView() {

        binding.imgFlashLight.setOnClickListener(v -> {
            if (isFlashlightOn) {
                binding.imgFlashLight.setImageResource(R.drawable.ic_lightning_deactivated);
                binding.scannerView.setFlash(false);
            } else {
                binding.imgFlashLight.setImageResource(R.drawable.ic_lightning_activated);
                binding.scannerView.setFlash(true);
            }

            isFlashlightOn = !isFlashlightOn;
        });

        binding.imgBack.setOnClickListener(v -> finish());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (binding.scannerView != null) {
            binding.scannerView.setResultHandler(this);
            binding.scannerView.setFormats(Collections.singletonList(BarcodeFormat.QR_CODE));
            binding.scannerView.setAutoFocus(true);
            binding.scannerView.setAspectTolerance(0.5f);
            binding.scannerView.startCamera();
            binding.scannerView.setFlash(false);
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        final String scanResult = rawResult.getText();
        Toast.makeText(getApplicationContext(), scanResult, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.scannerView.setFlash(false);
        binding.scannerView.stopCamera();
    }
}
