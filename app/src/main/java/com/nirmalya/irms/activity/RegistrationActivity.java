package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityRegistrationBinding;
import com.nirmalya.irms.model.request.SetPinRequest;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private Context context;
    private APIRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        init();
    }

    private void init() {

        context = this;
        repo = new APIRepo();

        binding.edtMobileNumber.setText(Osssc.getPrefs().getScannerMobile());

        binding.leftarrowimg.setOnClickListener(v -> onBackPressed());

        binding.saveButton.setOnClickListener(view -> validateData());
    }

    public void validateData() {
        if (binding.edtPin.getText().toString().trim().length() < 6) {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit pin");
        } else if (Utils.isNullOrEmpty(binding.edtConPin.getText().toString().trim())) {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit confirm pin");
        } else if (!binding.edtConPin.getText().toString().trim()
                .equals(binding.edtPin.getText().toString())) {
            MessageUtils.showFailureMessage(context, "Pin & confirm pin do not match");
        } else {
            //goNext();
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    private void goNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.setUpPin(new SetPinRequest(Osssc.getPrefs().getScannerMobile(),
                Utils.getDeviceIMEI(getApplicationContext()), binding.edtConPin.getText().toString()))
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }

                    pd.dismiss();
                });
    }
}
