package com.example.yashladha.android_seller;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
=======
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yashladha.android_seller.fragments.OrdersFrag;
>>>>>>> b2fac6aa0b479a44980949c858ea793ca2bb17b6


public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);
        setTitle("Orders");
<<<<<<< HEAD
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new OrdersFrag()).commit();
    }
=======
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, new OrdersFrag()).commit();
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(OrdersActivity.this,DisplayActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }*/

>>>>>>> b2fac6aa0b479a44980949c858ea793ca2bb17b6
}
