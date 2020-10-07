package com.nirmalya.governmentexams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {
    ImageView leftarrowimg;
    Button save_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        leftarrowimg = findViewById(R.id.leftarrowimg);
        save_button = findViewById(R.id.save_button);
        /* toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);*/
        leftarrowimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtpActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
                /*finishAffinity();*/


        });
    }

}
