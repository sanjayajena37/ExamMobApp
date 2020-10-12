
package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.model.request.SignInRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView password_text;
    TextView regdTextview;
    LinearLayout forgotpasswrdlayout;
    private APIRepo repo;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        repo = new APIRepo();

        password_text = findViewById(R.id.password_text);
        regdTextview = findViewById(R.id.regdTextview);
        forgotpasswrdlayout = findViewById(R.id.forgotpasswrdlayout);
        regdTextview.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, NumberVerifActivity.class);
            startActivity(intent);
        });

        forgotpasswrdlayout.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        //--------------------------------Password-------------------------

        password_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (password_text.getText().toString().trim().length() >= 6) {
                    //goNext();
                    Intent intent = new Intent(LoginActivity.this, DashbordActivity.class);
                    password_text.setText("");
                    startActivity(intent);
                    Osssc.getPrefs().setIsLoggedIn(false);
                    finish();
                }
            }
        });
    }

    private void goNext() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.signIn(new SignInRequest(Utils.getDeviceIMEI(getApplicationContext()),
                password_text.getText().toString().trim()))
                .observe(this, signInResponse -> {
                    if (signInResponse != null && signInResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, signInResponse.getMessage());
                        Osssc.getPrefs().setScannerData(signInResponse.getScannerData());
                        Intent intent = new Intent(LoginActivity.this, DashbordActivity.class);
                        password_text.setText("");
                        startActivity(intent);
                        Osssc.getPrefs().setIsLoggedIn(true);
                        finish();
                    }

                    pd.dismiss();
                });
    }
}

