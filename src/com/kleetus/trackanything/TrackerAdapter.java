package com.kleetus.trackanything;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TrackerAdapter extends SimpleCursorAdapter {

    private int layout;
    Context context;
    TextView trackerName;
    EditText trackerEdit;

    public TrackerAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flag) {
        super(context, layout, c, from, to, flag);
        this.layout = layout;
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        Cursor c = getCursor();

        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layout, parent, false);

        trackerName = (TextView) v.findViewById(R.id.tracker_name);
        trackerEdit = (EditText) v.findViewById(R.id.edit_tracker_name);

        String name = c.getString(1);

        if (null != trackerName) {

            if (name.equals("")) {

                trackerName.setVisibility(View.GONE);
                trackerEdit.setVisibility(View.VISIBLE);
                v.setBackgroundResource(R.color.royal_blue);
                trackerEdit.setFocusable(true);

            }

            trackerName.setText(name);

        }
        return v;
    }


    @Override
    public void bindView(View v, Context context, Cursor c) {

        trackerName = (TextView) v.findViewById(R.id.tracker_name);
        trackerEdit = (EditText) v.findViewById(R.id.edit_tracker_name);

        String name = c.getString(1);

        if (null != trackerName) {

            trackerName.setText(name);

        }

    }
}



