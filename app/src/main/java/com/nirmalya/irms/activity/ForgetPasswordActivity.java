package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityForgetpasswordBinding;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

public class ForgetPasswordActivity extends AppCompatActivity {

    private ActivityForgetpasswordBinding binding;
    private Context context;
    private APIRepo repo;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgetpassword);

        init();
    }

    private void init() {

        context = this;
        repo = new APIRepo();

        binding.btnNext.setOnClickListener(view -> validateData());

        binding.leftarrowimg.setOnClickListener(v -> finish());
    }

    public void validateData() {
        if (!Utils.isNullOrEmpty(binding.edtMobile.getText().toString().trim()) &&
                binding.edtMobile.getText().toString().trim().length() == 10) {
            Osssc.getPrefs().setScannerMobile(binding.edtMobile.getText().toString().trim());
            gotoNext();
        } else {
            MessageUtils.showFailureMessage(context, "Please enter valid 10 digit mobile number");
        }
    }

    private void gotoNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.forgotPin(new SignupSendMobileRequest(binding.edtMobile.getText().toString(),
                Utils.getDeviceIMEI(getApplicationContext())))
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                        Intent intent = new Intent(ForgetPasswordActivity.this, OtpActivity.class);
                        intent.putExtra("priviousScreen", "ForgetPasswordActivity");
                        startActivity(intent);
                    }

                    pd.dismiss();
                });
    }
}
