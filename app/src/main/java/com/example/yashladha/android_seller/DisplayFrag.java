package com.example.yashladha.android_seller;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DisplayFrag extends Fragment {

    public DisplayFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.product_display_list, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.tvListView);

        final ArrayList<Product> products = new ArrayList<>();

        ProductAdapter productAdapter = new ProductAdapter(getActivity(),products,R.color.back);

        return rootView;
    }

}
