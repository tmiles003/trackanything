package com.kleetus.trackanything;


import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TrackAnything extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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


    private void saveTracker() {    }

    private void addTracker() {    }

}
