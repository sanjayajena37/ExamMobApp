package com.nirmalya.irms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nirmalya.irms.R;

public class SetNewpinactivity extends AppCompatActivity {
    Button update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnewpin);
        update_button = findViewById(R.id.update_button);
        update_button.setOnClickListener(view -> {
            Intent intent=new Intent(SetNewpinactivity.this, LoginActivity.class);
            startActivity(intent);

        });
    }
}