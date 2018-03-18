package com.example.yashladha.android_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        setTitle("Sales");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new SalesFrag()).commit();
    }
}
