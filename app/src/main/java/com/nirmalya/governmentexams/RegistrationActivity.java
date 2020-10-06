package com.nirmalya.governmentexams;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
public class RegistrationActivity extends AppCompatActivity {
    Button save_button;
    ImageView leftarrowimg;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }


}
