package com.nirmalya.irms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nirmalya.irms.R;

import androidx.appcompat.app.AppCompatActivity;


public class ChangePinActivity extends AppCompatActivity {
Button update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepin);
        update_button = findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangePinActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
