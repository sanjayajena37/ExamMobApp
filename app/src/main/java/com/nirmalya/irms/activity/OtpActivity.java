


package com.nirmalya.irms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityOtpBinding;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;
    private Bundle extras;
    private String previousScreen;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);

        context = this;

        if (getIntent() != null) {
            extras = getIntent().getExtras();
            if (extras != null) {
                previousScreen = extras.getString("priviousScreen");
            }
        }
        if(previousScreen.equalsIgnoreCase("NumberVerifActivity")) {
            binding.pageloaderimage.setVisibility(View.VISIBLE);
            binding.step1textv.setVisibility(View.VISIBLE);
        }else {
            binding.pageloaderimage.setVisibility(View.GONE);
            binding.step1textv.setVisibility(View.GONE);
        }
        binding.leftarrowimg.setOnClickListener(v -> onBackPressed());

        binding.saveButton.setOnClickListener(v -> validateData());
        binding.mobilenotextView.setText("OTP has been sent to you on your Mobile Number."+Osssc.getPrefs().getScannerMobile());
    }

    public void validateData() {
        if (!Utils.isNullOrEmpty(binding.edtOTP.getText().toString().trim()) &&
                binding.edtOTP.getText().toString().trim().length() == 6) {
            if(previousScreen.equalsIgnoreCase("NumberVerifActivity")) {

                Intent intent=new Intent(OtpActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent=new Intent(OtpActivity.this, SetNewpinactivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit OTP");
        }
    }
}

