


package com.nirmalya.irms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nirmalya.irms.R;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {
    ImageView leftarrowimg;
    Button save_button;
    private Bundle extras;
    private String previousScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        if (getIntent() != null) {
            extras = getIntent().getExtras();
            if (extras != null) {
                previousScreen = extras.getString("priviousScreen");
            }
        }

        save_button = findViewById(R.id.save_button);

        leftarrowimg = findViewById(R.id.leftarrowimg);
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
                if(previousScreen.equalsIgnoreCase("NumberVerifActivity")) {
                    Intent intent=new Intent(OtpActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent=new Intent(OtpActivity.this, SetNewpinactivity.class);
                    startActivity(intent);
                }

                /*finishAffinity();*/

            }
        });
    }

}

