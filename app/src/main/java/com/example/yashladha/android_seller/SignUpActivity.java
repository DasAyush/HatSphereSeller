package com.example.yashladha.android_seller;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView tvUsername,tvEmail,tvPassword,tvHaveAnAccount,tvOrLoginBy;
    TextInputEditText etUsername,etPassword,etEmail;
    Button btRegister,btFacebook,btGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign Up");
        setContentView(R.layout.register);

        tvUsername = (TextView)findViewById(R.id.tvUsername);
        tvPassword = (TextView)findViewById(R.id.tvPassword);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvHaveAnAccount = (TextView)findViewById(R.id.tvHaveAnAccount);
        tvOrLoginBy = (TextView)findViewById(R.id.tvOrLoginBy);

        etUsername = (TextInputEditText)findViewById(R.id.etUsername);
        etPassword = (TextInputEditText)findViewById(R.id.etPassword);
        etEmail = (TextInputEditText)findViewById(R.id.etEmail);

        btRegister = (Button)findViewById(R.id.btRegister);
        btFacebook = (Button)findViewById(R.id.btFacebook);
        btGoogle = (Button)findViewById(R.id.btGoogle);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
