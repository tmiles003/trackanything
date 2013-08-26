package com.kleetus.trackanything;


import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;

public class TrackAnything extends ActionBarActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
