package com.example.yashladha.android_seller;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;
import android.widget.TextView;

import com.example.yashladha.android_seller.classes.SimpleFragmentPagerAdapter;
import com.example.yashladha.android_seller.fragments.DisplayFrag;

//import android.widget.Toolbar;


public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*private int[] icons = {
            R.drawable.display_icon,
            R.drawable.sales_analysis_icon,
            R.drawable.orders_icon
    };*/

    TabLayout tabLayout;
    ViewPager viewPager;
    private String mActivityTitle;
    private Menu menu;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * We customized the function that this activity will show when we touch the back key
     */

    protected void exitByBackKey() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        /*else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }*/
        else {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("Do you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            finish();
                            //close();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    })
                    .show();


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mActivityTitle = getTitle().toString();

        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(simpleFragmentPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs1);

        tabLayout.setupWithViewPager(viewPager);
        //fabAdd = (FloatingActionButton) findViewById(R.id.fabAddProduct);
        /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }*/

        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_drawer);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setVerticalScrollBarEnabled(true);
        setupDrawer();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    /**
     * This function helps us to setup the navigation drawer as per our adapter
     */
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("HatSphere");

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
/*        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        */

        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exitByBackKey();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings || mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/

        if ( mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method helps us to choose items from the navigation itsms eelected
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.itMyAccount) {
            Intent i = new Intent(HomePageActivity.this, NavigationDrawerItems.class);
            i.putExtra("Type", "My Account");
            startActivity(i);

        } else if (id == R.id.itHelp) {
            Intent i = new Intent(HomePageActivity.this, NavigationDrawerItems.class);
            i.putExtra("Type", "Help");
            startActivity(i);

        } else if (id == R.id.itAboutUs) {
            Intent i = new Intent(HomePageActivity.this, NavigationDrawerItems.class);
            i.putExtra("Type", "About Us");
            startActivity(i);

        } else if (id == R.id.itFaq) {
            Intent i = new Intent(HomePageActivity.this, NavigationDrawerItems.class);
            i.putExtra("Type", "FAQs");
            startActivity(i);
        } else if (id == R.id.itAddProduct) {
            Intent i = new Intent(HomePageActivity.this, AddProductsActivity.class);
            startActivity(i);
        } else if (id == R.id.itLogOut) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Log Out")
                    .setMessage("Do you really want to log out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logOut();
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DisplayFrag()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * This method deletes the shared prefs of the user when he tries to checkout
     */
    public void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("myprfs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }
}
