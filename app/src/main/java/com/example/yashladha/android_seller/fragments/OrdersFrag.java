package com.example.yashladha.android_seller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.data.Order;
import com.example.yashladha.android_seller.data.OrderAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFrag extends Fragment {

    public OrdersFrag() {
        // Required empty public constructor
    }

    public static OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.orders_list, container, false);

        final ArrayList<Order> orders = new ArrayList<Order>();

        orders.add(new Order("Baskets", "₹400","24-07-2017", "Accept",
                "Pending",  R.drawable.order_baskets));
        orders.add(new Order("Handbags", "₹1300", "24-07-2017", "Accept",
                "Pending", R.drawable.order_handbags));
        orders.add(new Order("Sandals", "₹1500","24-07-2017", "Accept",
                "Pending", R.drawable.order_sandals));
        orders.add(new Order("Candles", "₹220", "24-07-2017", "Accept",
                "Pending", R.drawable.orders_candles));
        orders.add(new Order("Duck", "₹350", "24-07-2017", "Accept",
                "Pending", R.drawable.orders_duck));
        orders.add(new Order("Owl", "₹300", "24-07-2017", "Accept",
                "Pending", R.drawable.orders_owl));
        orders.add(new Order("Vase", "₹1200","24-07-2017", "Accept",
                "Pending", R.drawable.orders_vase));


        orderAdapter = new OrderAdapter(getActivity(), orders, R.color.back5);
        ListView listView = (ListView) rootview.findViewById(R.id.lvOrderList);
        listView.setAdapter(orderAdapter);

        return rootview;
    }

}
