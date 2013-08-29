package com.kleetus.trackanything;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainListFragment extends ListFragment {
    SimpleCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] projection = {Constants.COL_ID, Constants.COL_TRACKER_NAME };
        String[] uiBindFrom = { Constants.COL_TRACKER_NAME };
        int[] uiBindTo = { android.R.id.list };

        Cursor c = getActivity().getContentResolver().query(
                MainContentProvider.CONTENT_URI, projection, null, null, null);

        CursorAdapter adapter = new SimpleCursorAdapter(getActivity()
                .getApplicationContext(), R.layout.main_list, c,
                uiBindFrom, uiBindTo);

        setListAdapter(adapter);

        return inflater.inflate(R.layout.main_list, container, false);
    }


}

