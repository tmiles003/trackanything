package com.kleetus.trackanything;


import android.database.Cursor;
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

public class MainListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    String[] projection = {Constants.COL_ID, Constants.COL_TRACKER_NAME};
    String[] uiBindFrom = {Constants.COL_TRACKER_NAME};
    int[] uiBindTo = {android.R.id.text1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getLoaderManager().initLoader(0, null, this);

        return inflater.inflate(R.layout.main_list, container, false);
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

        CursorAdapter adapter = new SimpleCursorAdapter(getActivity()
                .getApplicationContext(), android.R.layout.simple_list_item_1, cursor,
                uiBindFrom, uiBindTo);

        setListAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }
}

