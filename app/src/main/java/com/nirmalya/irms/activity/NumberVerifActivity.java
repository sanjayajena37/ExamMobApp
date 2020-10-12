package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.FragmentSignupMobileBinding;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

public class NumberVerifActivity extends AppCompatActivity {

    private FragmentSignupMobileBinding binding;
    String value1 = "numsendOTP";
    private Context context;
    private APIRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_signup_mobile);

        context = this;
        repo = new APIRepo();

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
            Osssc.getPrefs().setScannerMobile(binding.edtMobile.getText().toString().trim());
            //gotoNext();
            Intent intent = new Intent(NumberVerifActivity.this, OtpActivity.class);
            intent.putExtra("priviousScreen", "NumberVerifActivity");
            startActivity(intent);
        } else {
            MessageUtils.showFailureMessage(context, "Please enter valid 10 digit mobile number");
        }
    }

    private void gotoNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.sendOTP(new SignupSendMobileRequest(binding.edtMobile.getText().toString(),
                Utils.getDeviceIMEI(getApplicationContext())))
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                        Intent intent = new Intent(NumberVerifActivity.this, OtpActivity.class);
                        intent.putExtra("priviousScreen", "NumberVerifActivity");
                        startActivity(intent);
                    }

                    pd.dismiss();
                });
    }
}
