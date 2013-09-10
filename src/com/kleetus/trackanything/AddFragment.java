package com.kleetus.trackanything;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.*;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private String[] projection = { Constants.COL_ID, Constants.COL_KVP_KEY };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //pullExistingTrackerElements();
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.add_tracker, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Button addKvpButton = (Button) getActivity().findViewById(R.id.button_add_kvp);

        addKvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText kvpLabel = (EditText)getActivity().findViewById(R.id.edit_kvp_label);

            }
        });

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        menu.clear();
        menuInflater.inflate(R.menu.add_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void pullExistingTrackerElements() {

        getLoaderManager().initLoader(Constants.MAIN_LOADER, null, this);

    }

}
