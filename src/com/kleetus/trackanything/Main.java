package com.kleetus.trackanything;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Main extends ActionBarActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    CharSequence drawerTitle;
    CharSequence title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        title = drawerTitle = getTitle();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(title);
                supportInvalidateOptionsMenu();

            }

            public void onDrawerOpened(View view) {

                getSupportActionBar().setTitle(drawerTitle);
                supportInvalidateOptionsMenu();

            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        loadTracker();

        loadDashboard();

    }

    private void loadDashboard() {

        Fragment dashboardFragment = new DashboardFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, dashboardFragment).commit();

    }

    private void loadTracker() {

        Fragment trackerFragment = new TrackerListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.list_frame, trackerFragment).commit();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.add_menu:
                addTracker();
                return true;
            case R.id.save_menu:
                saveTracker();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void saveTracker() {


        loadDefault();
    }

    private void addTracker() {

        //add a row to tracker list and make the keyboard the first responder
        TrackerListFragment trackerFragment = (TrackerListFragment) getSupportFragmentManager().findFragmentById(R.id.list_frame);
        if(trackerFragment.isAdded()) {
            trackerFragment.addTracker();
        }


//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        AddFragment addFragment = new AddFragment();
//        ft.replace(R.id.content_frame, addFragment);
//        ft.addToBackStack(null);
//        ft.commit();

    }

    private void loadDefault() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment dashboardFragment = new DashboardFragment();
        ft.replace(R.id.content_frame, dashboardFragment);
        ft.addToBackStack(null);
        ft.commit();

    }


}
