


package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityOtpBinding;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.model.request.ValidateOTPRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;
    private Bundle extras;
    private String previousScreen;
    private Context context;
    private APIRepo repo;

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

        binding.leftarrowimg.setOnClickListener(v -> onBackPressed());

        binding.saveButton.setOnClickListener(v -> validateData());

        //binding.btnResendOTP.setOnClickListener(v -> resendOTP());
    }

    public void validateData() {
        if (!Utils.isNullOrEmpty(binding.edtOTP.getText().toString().trim()) &&
                binding.edtOTP.getText().toString().trim().length() == 6) {
            //gotoNext();
            if (previousScreen.equalsIgnoreCase("NumberVerifActivity")) {
                Intent intent = new Intent(OtpActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(OtpActivity.this, SetNewpinactivity.class);
                startActivity(intent);
                finish();
            }
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

