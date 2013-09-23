package com.kleetus.trackanything;


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddTrackerFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_tracker, parent, false);
    }


    public void saveTracker() {

        String[] inputs = {((EditText) getActivity().findViewById(R.id.edit_tracker_nane)).getText().toString(),
                ((EditText) getActivity().findViewById(R.id.edit_kvp_label)).getText().toString(),
                ((Spinner) getActivity().findViewById(R.id.types_spinner)).getSelectedItem().toString(),
                ((Spinner) getActivity().findViewById(R.id.colors_spinner)).getSelectedItem().toString(),
                ((Spinner) getActivity().findViewById(R.id.graph_spinner)).getSelectedItem().toString()
        };

        int errors = validateInput(inputs);

        if (errors < 1) {

            ContentValues insertedValue = new ContentValues();
            insertedValue.put(Constants.COL_TRACKER_NAME, inputs[0]);

            Uri insertedUri = getActivity().getContentResolver().insert(
                    Constants.CONTENT_URI,
                    insertedValue
            );

            String[] uriParts = insertedUri.toString().split("/");
            int trackerId =  Integer.parseInt(uriParts[uriParts.length-1]);



        }

        else {

            //deal with the errors

        }


    }

    private int validateInput(String[] inputs) {

        int errors = 0;

        for(String input : inputs) {

            if(input.length() < 1) {

                errors++;

            }

        }

        return errors;

    }

}
