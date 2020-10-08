package com.nirmalya.irms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nirmalya.irms.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button next_button;
    ImageView leftarrowimg;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        next_button = findViewById(R.id.next_button);
        leftarrowimg = findViewById(R.id.leftarrowimg);
        next_button.setOnClickListener(view -> {
            Intent intent=new Intent(ForgetPasswordActivity.this, OtpActivity.class);
            intent.putExtra("priviousScreen", "ForgetPasswordActivity");
            startActivity(intent);

        });

        leftarrowimg.setOnClickListener(v -> finish());
    }
}
