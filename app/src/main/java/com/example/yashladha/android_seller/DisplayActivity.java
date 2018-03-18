package com.example.yashladha.android_seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_display_list);
        setTitle("Products");
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new DisplayFrag()).commit();
    }
}
