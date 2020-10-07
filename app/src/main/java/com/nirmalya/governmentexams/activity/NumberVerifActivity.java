package com.nirmalya.governmentexams.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nirmalya.governmentexams.R;

public class NumberVerifActivity extends AppCompatActivity {
    TextView logintext;
    Button otp_button;
    String value1 = "numsendOTP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup_mobile);
        otp_button = findViewById(R.id.otp_button);
        logintext = findViewById(R.id.logintext);
        otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NumberVerifActivity.this, OtpActivity.class);
                intent.putExtra("priviousScreen", "NumberVerifActivity");
                startActivity(intent);

            }
        });
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NumberVerifActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
