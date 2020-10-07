package com.nirmalya.governmentexams.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nirmalya.governmentexams.R;

public class SetNewpinactivity extends AppCompatActivity {
    Button update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnewpin);
        update_button = findViewById(R.id.next_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetNewpinactivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
