


package com.nirmalya.irms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        binding.leftarrowimg.setOnClickListener(v -> onBackPressed());

        binding.saveButton.setOnClickListener(v -> validateData());
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

