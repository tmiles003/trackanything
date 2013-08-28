package com.kleetus.trackanything;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class MainListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] projection = { Constants.COL_ID, Constants.COL_TRACKER_NAME };
        String[] uiBindFrom = { Constants.COL_TRACKER_NAME };
        int[] uiBindTo = { android.R.id.list };
        Cursor cursor = getActivity().getContentResolver().query(MainContentProvider.CONTENT_URI, projection, null, null, null);
        CursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.main_list, cursor, uiBindFrom, uiBindTo, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.main_list, container, false);
    }

}
