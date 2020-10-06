package com.nirmalya.governmentexams;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView textTitle, txtsubTitle,password_text;
    TextView regdTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password_text = findViewById(R.id.password_text);
        regdTextview = findViewById(R.id.regdTextview);
        regdTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
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
                if(password_text.getText().toString().trim().length() >=4){
                    Intent intent=new Intent(LoginActivity.this, DashbordActivity.class);
                    password_text.setText("");
                    startActivity(intent);


                }

            }
        });

    }


}
