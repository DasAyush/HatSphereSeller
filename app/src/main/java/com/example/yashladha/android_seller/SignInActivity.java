package com.example.yashladha.android_seller;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    TextView tvUsername,tvPassword,tvRegisterHere,tvForgotPassword,tvOrLoginBy;
    TextInputEditText etUsername,etPassword;
    Button btLogin,btFacebook,btGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LogIn");
        setContentView(R.layout.login);

        tvUsername = (TextView)findViewById(R.id.tvUsername);
        tvPassword = (TextView)findViewById(R.id.tvPassword);
        tvRegisterHere = (TextView)findViewById(R.id.tvRegisterHere);
        tvForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        tvOrLoginBy = (TextView)findViewById(R.id.tvOrLoginBy);

        etUsername = (TextInputEditText)findViewById(R.id.etUsername);
        etPassword = (TextInputEditText)findViewById(R.id.etPassword);

        btLogin = (Button)findViewById(R.id.btLogin);
        btFacebook = (Button)findViewById(R.id.btFacebook);
        btGoogle = (Button)findViewById(R.id.btGoogle);

        btLogin.setOnClickListener(new View.OnClickListener() {
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
