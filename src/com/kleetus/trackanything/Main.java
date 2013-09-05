package com.kleetus.trackanything;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class Main extends TrackAnything {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        load_tracker_fragment();

        load_body_fragment();

    }

    private void load_body_fragment() {

        Fragment addFragment = new AddFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, addFragment).commit();

    }

    private void load_tracker_fragment() {

        Fragment trackerFragment = new MainListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.list_frame, trackerFragment).commit();

    }


}
