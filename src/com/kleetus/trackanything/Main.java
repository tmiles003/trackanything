package com.kleetus.trackanything;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class Main extends ActionBarActivity implements NavDrawerInterface {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    CharSequence drawerTitle;
    CharSequence title;
    TrackerListFragment trackerFragment;
    boolean isSaveContext = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.main);

        title = drawerTitle = getTitle();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(title);
                //supportInvalidateOptionsMenu();

            }

            public void onDrawerOpened(View view) {

                getSupportActionBar().setTitle(drawerTitle);
                //supportInvalidateOptionsMenu();

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

        trackerFragment = new TrackerListFragment();
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
                supportInvalidateOptionsMenu();
                return true;
            case R.id.delete_all_trackers:
                if (null != trackerFragment) {
                    trackerFragment.deleteAllTrackers();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(isSaveContext) {
            menu.findItem(R.id.save_menu).setVisible(true);
            menu.findItem(R.id.add_menu).setVisible(false);
            isSaveContext = false;
        }

        MenuItem deleteAll = menu.findItem(R.id.delete_all_trackers);

        if (null != deleteAll && MainApplication.DEBUG) {

            deleteAll.setVisible(true);

        }

        return super.onCreateOptionsMenu(menu);
    }

    private void saveTracker() {

        loadDefault();
    }

    private void addTracker() {

        if (trackerFragment.isAdded()) {

            trackerFragment.addTracker();

            AddTrackerFragment addTrackerFragment = new AddTrackerFragment();

            if (!addTrackerFragment.isAdded()) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, addTrackerFragment);
                ft.addToBackStack(null);
                ft.commit();
                replaceWithSaveMenu();
            }

        }

    }

    private void replaceWithSaveMenu() {

        isSaveContext = true;

        supportInvalidateOptionsMenu();

    }

    private void loadDefault() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment dashboardFragment = new DashboardFragment();
        ft.replace(R.id.content_frame, dashboardFragment);
        ft.addToBackStack(null);
        ft.commit();

    }


    @Override
    public void openDrawer() {

        drawerLayout.openDrawer(Gravity.LEFT);

    }

    @Override
    public void closeDrawer() {

        drawerLayout.closeDrawer(Gravity.LEFT);

    }
}
