
package com.nirmalya.irms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.utility.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView password_text;
    TextView regdTextview;
    LinearLayout forgotpasswrdlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                    String deviceId = Utils.getDeviceIMEI(getApplicationContext());
                    Intent intent = new Intent(LoginActivity.this, DashbordActivity.class);
                    password_text.setText("");
                    startActivity(intent);
                    Osssc.getPrefs().setIsLoggedIn(false);
                    finish();
                }
            }
        });

    }
}

