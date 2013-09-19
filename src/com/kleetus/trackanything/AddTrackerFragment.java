package com.kleetus.trackanything;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

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

        String[] errors = validateInput(inputs);

//        if (errors.length > 0) {
//
//            ContentValues insertedValue = new ContentValues();
//            insertedValue.put(Constants.COL_TRACKER_NAME, inputs[0]);
//            insertedValue.put(Constants.COL_, inputs[0]);
//            insertedValue.put(Constants.COL_TRACKER_NAME, inputs[0]);
//            insertedValue.put(Constants.COL_TRACKER_NAME, inputs[0]);
//
//
//            Uri insertedUri = getActivity().getContentResolver().insert(
//                    MainContentProvider.CONTENT_URI,
//                    insertedValue
//            );
//
//        }
//

    }

    private String[] validateInput(String[] inputs) {

        return null;

    }

}
