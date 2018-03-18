package com.example.yashladha.android_seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity_2 extends AppCompatActivity {
    private TextView tvName, tvPassword, tvRegister, tvForgotPassword, tvNumChar, tvCharLeft;
    private Button btProcees;
    private EditText etAddress, etContact;
    private EditText etName;
    private CheckBox cbTrial;
    private String address = "", contact = "", name = "";
    private Boolean trial = false;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        tvNumChar = (TextView) findViewById(R.id.tvNumChar);
        tvCharLeft = (TextView) findViewById(R.id.tvCharLeft);
        etName = (EditText) findViewById(R.id.etName);
        btProcees = (Button) findViewById(R.id.btProceed);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etContact = (EditText) findViewById(R.id.etContact);
        cbTrial = (CheckBox) findViewById(R.id.cbTrial);
        Intent i = getIntent();
        profileImage = i.getStringExtra("profileImage");
        trial = false;
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etName.getText().toString().trim().length() < 5) {
                        etName.setError("Minimum length should be 5 characters");
                    } else {
                        etName.setError(null);
                    }
                }
            }
        });

        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This sets a textview to the current length
                tvNumChar.setText(String.valueOf(s.length()));
            }

            public void afterTextChanged(Editable s) {
            }
        };

        btProcees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!trial && !address.equals("") && !contact.equals("") && !name.equals("")) {
                    Intent i = new Intent(RegisterActivity_2.this, PlanChoicesActivity.class);
                    i.putExtra("address", address);
                    i.putExtra("contact", contact);
                    i.putExtra("name", name);
                    i.putExtra("profileImage", profileImage);
                    startActivity(i);
                } else if (trial && !address.equals("") && !contact.equals("") && !name.equals("")) {
                    Intent i = new Intent(RegisterActivity_2.this, TrialActivity.class);
                    i.putExtra("address", address);
                    i.putExtra("contact", contact);
                    i.putExtra("profileImage", profileImage);
                    i.putExtra("name", name);
                    startActivity(i);
                } else {
                    Toast.makeText(RegisterActivity_2.this, "Some fields are missing", Toast.LENGTH_LONG).show();

                }
            }
        });
        cbTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTrial.isChecked()) {
                    trial = true;
                }
                else{
                    trial = false;
                }
            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = etName.getText().toString().trim();
            }
        });

        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                address = etAddress.getText().toString().trim();
            }
        });
        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact = etContact.getText().toString().trim();
            }
        });
    }
}
