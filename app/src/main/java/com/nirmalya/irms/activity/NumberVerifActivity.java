package com.nirmalya.irms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.FragmentSignupMobileBinding;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class NumberVerifActivity extends AppCompatActivity {

    private FragmentSignupMobileBinding binding;
    String value1 = "numsendOTP";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_signup_mobile);

        context = this;

        binding.edtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.edtMobile.getText().toString().trim().length() == 10) {
                    Utils.hideKeyboard(context);
                }
            }
        });

        binding.otpBtn.setOnClickListener(view -> validateData());

        binding.logintext.setOnClickListener(view -> {
            Intent intent = new Intent(NumberVerifActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void validateData() {
        if (!Utils.isNullOrEmpty(binding.edtMobile.getText().toString().trim()) &&
                binding.edtMobile.getText().toString().trim().length() == 10) {
            Intent intent = new Intent(NumberVerifActivity.this, OtpActivity.class);
            intent.putExtra("priviousScreen", "NumberVerifActivity");
            startActivity(intent);
        } else {
            MessageUtils.showFailureMessage(context, "Please enter valid 10 digit mobile number");
        }
    }
}
