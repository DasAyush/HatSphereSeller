package com.example.yashladha.android_seller;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SalesActivity extends AppCompatActivity {

=======
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yashladha.android_seller.data.MyRecyclerViewAdapter;
import com.example.yashladha.android_seller.data.RecyclerDataObjects;
import com.example.yashladha.android_seller.fragments.SalesFrag;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class SalesActivity extends AppCompatActivity {

    private Spinner mMonthSpinner;
    String mMonth = getString(R.string.mon_jan);
    /*PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;*/
    TextView tvDate, tvMostBought, tvMostProductName, tvAnalysis;
    SimpleDateFormat formatter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


>>>>>>> b2fac6aa0b479a44980949c858ea793ca2bb17b6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        setTitle("Sales");
<<<<<<< HEAD
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new SalesFrag()).commit();
    }
=======
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, new SalesFrag()).commit();

        mLayoutManager = new LinearLayoutManager(this);

        mMonthSpinner = (Spinner) findViewById(R.id.spinner_month);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(mMonthSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(200);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }


        tvMostBought = (TextView) findViewById(R.id.tvMostBought);
        tvMostProductName = (TextView) findViewById(R.id.tvMostProductName);
        tvAnalysis = (TextView) findViewById(R.id.tvAnalysis);

        setupSpinner();
        /*pieChart = (PieChart) findViewById(R.id.piechart);

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);
        */

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter monthSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_month_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        monthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mMonthSpinner.setAdapter(monthSpinnerAdapter);


        // Set the integer mSelected to the constant values
        mMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.mon_jan))) {
                        mMonth = "January";
                    } else if (selection.equals(getString(R.string.mon_feb))) {
                        mMonth = "February";
                    } else if (selection.equals(getString(R.string.mon_mar))) {
                        mMonth = "March";
                    } else if (selection.equals(getString(R.string.mon_apr))) {
                        mMonth = "April";
                    } else if (selection.equals(getString(R.string.mon_may))) {
                        mMonth = "May";
                    } else if (selection.equals(getString(R.string.mon_jun))) {
                        mMonth = "June";
                    } else if (selection.equals(getString(R.string.mon_jul))) {
                        mMonth = "July";
                    } else if (selection.equals(getString(R.string.mon_aug))) {
                        mMonth = "August";
                    } else if (selection.equals(getString(R.string.mon_sep))) {
                        mMonth = "September";
                    } else if (selection.equals(getString(R.string.mon_oct))) {
                        mMonth = "October";
                    } else if (selection.equals(getString(R.string.mon_nov))) {
                        mMonth = "November";
                    } else if (selection.equals(getString(R.string.mon_dec))) {
                        mMonth = "December";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mMonth = "January";
            }
        });
    }

    /*
    public void AddValuesToPIEENTRY() {

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));

    }

    public void AddValuesToPieEntryLabels() {

        PieEntryLabels.add("January");
        PieEntryLabels.add("February");
        PieEntryLabels.add("March");
        PieEntryLabels.add("April");
        PieEntryLabels.add("May");
        PieEntryLabels.add("June");

    }
    */

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<RecyclerDataObjects> getDataSet() {
        ArrayList results = new ArrayList<RecyclerDataObjects>();

        /*for (int index = 0; index < 20; index++) {
            RecyclerDataObjects obj = new RecyclerDataObjects("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        */

        results.add(0,new RecyclerDataObjects("BEST SELLER"+ 0,"BASKETS"+0));
        return results;
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(SalesActivity.this,DisplayActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }*/
>>>>>>> b2fac6aa0b479a44980949c858ea793ca2bb17b6
}
