package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivitySetnewpinBinding;
import com.nirmalya.irms.model.request.SetPinRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

public class SetNewpinactivity extends AppCompatActivity {

    private ActivitySetnewpinBinding binding;
    private Context context;
    private APIRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setnewpin);

        init();
    }

    private void init() {

        context = this;
        repo = new APIRepo();

        binding.btnSubmit.setOnClickListener(view -> validateData());
    }

    public void validateData() {
        if (binding.edtPin.getText().toString().trim().length() < 6) {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit pin");
        } else if(Utils.isNullOrEmpty(binding.edtConPin.getText().toString().trim())) {
            MessageUtils.showFailureMessage(context, "Please enter 6 digit confirm pin");
        } else if(!binding.edtConPin.getText().toString().trim()
                .equals(binding.edtPin.getText().toString())) {
            MessageUtils.showFailureMessage(context, "Pin & confirm pin do not match");
        } else {
            goNext();
        }
    }

    private void goNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.setUpPin(new SetPinRequest(Osssc.getPrefs().getScannerMobile(),
                Utils.getDeviceIMEI(context), binding.edtConPin.getText().toString()))
                .observe(this, commonResponse -> {
                    if (commonResponse != null && commonResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                        Intent intent = new Intent(SetNewpinactivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    pd.dismiss();
                });
    }
}
