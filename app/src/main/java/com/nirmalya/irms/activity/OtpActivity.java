


package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityOtpBinding;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.model.request.ValidateOTPRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;
    private Bundle extras;
    private String previousScreen;
    private Context context;
    private APIRepo repo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);

        context = this;
        repo = new APIRepo();

        if (getIntent() != null) {
            extras = getIntent().getExtras();
            if (extras != null) {
                previousScreen = extras.getString("priviousScreen");
            }
        }
        if(Objects.requireNonNull(previousScreen).equalsIgnoreCase("NumberVerifActivity")) {
            binding.pageloaderimage.setVisibility(View.VISIBLE);
            binding.step1textv.setVisibility(View.VISIBLE);
        }else {
            binding.pageloaderimage.setVisibility(View.GONE);
            binding.step1textv.setVisibility(View.GONE);
        }
        binding.leftarrowimg.setOnClickListener(v -> onBackPressed());

        binding.saveButton.setOnClickListener(v -> validateData());

        binding.btnResendOTP.setOnClickListener(v -> resendOTP());

        binding.mobilenotextView.setText("OTP has been sent to you on your Mobile Number "+Osssc.getPrefs().getScannerMobile());
    }

    public void validateData() {
        if (!Utils.isNullOrEmpty(binding.edtOTP.getText().toString().trim()) &&
                binding.edtOTP.getText().toString().trim().length() == 6) {
            gotoNext();
        } else {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit OTP");
        }
    }

    private void gotoNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        ValidateOTPRequest validateOTPRequest = new ValidateOTPRequest(Osssc.getPrefs().getScannerMobile(),
                Utils.getDeviceIMEI(context), binding.edtOTP.getText().toString().trim());

        repo.validateOTP(context, validateOTPRequest)
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                        if (previousScreen.equalsIgnoreCase("NumberVerifActivity")) {
                            Intent intent = new Intent(OtpActivity.this, RegistrationActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(OtpActivity.this, SetNewpinactivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    pd.dismiss();
                });
    }

    private void resendOTP() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.reSendOTP(new SignupSendMobileRequest(Osssc.getPrefs().getScannerMobile(),
                Utils.getDeviceIMEI(getApplicationContext())))
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                    }

                    pd.dismiss();
                });
    }
}

