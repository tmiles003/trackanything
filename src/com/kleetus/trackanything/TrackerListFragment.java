package com.kleetus.trackanything;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TrackerListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    String[] projection = {Constants.COL_ID, Constants.COL_TRACKER_NAME};
    String[] uiBindFrom = {Constants.COL_TRACKER_NAME};
    int[] uiBindTo = {android.R.id.text1};
    ListView trackerList;
    CursorAdapter listAdapter;
    Uri insertedUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getLoaderManager().initLoader(Constants.MAIN_LOADER, null, this);

        return inflater.inflate(R.layout.tracker_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        trackerList = getListView();

        trackerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                confirmDeleteTracker(l);

                return true;
            }
        });

    }

    private void confirmDeleteTracker(final long id) {

        new AlertDialog.Builder(getActivity())
                .setTitle("Delete Tracker ")
                .setMessage("Are you sure you would like to delete this tracker")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int count = deleteTracker(id);

                        if (count > 0) {
                            getLoaderManager().restartLoader(Constants.MAIN_LOADER, null, TrackerListFragment.this);
                        }

                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    private int deleteTracker(long id) {
        int count = getActivity().getContentResolver().delete(
                MainContentProvider.CONTENT_URI,
                "_id=" + Long.toString(id),
                null
        );
        return count;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(
                getActivity(),
                MainContentProvider.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

        listAdapter = new SimpleCursorAdapter(getActivity()
                .getApplicationContext(), android.R.layout.simple_list_item_1, cursor,
                uiBindFrom, uiBindTo, 0);


        setListAdapter(listAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

    @Override
    public void onListItemClick(ListView i, View v, int position, long id) {

        //load a new fragment??

        // getLoaderManager().restartLoader(Constants.MAIN_LOADER, null, this);

    }


    public void addTracker() {
        //add a new row to the list view and make the keyboard the first responder

        ContentValues insertedValue = new ContentValues();
        insertedValue.put("name", "");

        insertedUri = getActivity().getContentResolver().insert(
                MainContentProvider.CONTENT_URI,
                insertedValue
        );

        getLoaderManager().restartLoader(Constants.MAIN_LOADER, null, TrackerListFragment.this);

    }
}

