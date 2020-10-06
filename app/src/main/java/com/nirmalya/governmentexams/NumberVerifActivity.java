package com.nirmalya.governmentexams;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NumberVerifActivity extends AppCompatActivity {
    TextView logintext;
    Button otp_button;
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
