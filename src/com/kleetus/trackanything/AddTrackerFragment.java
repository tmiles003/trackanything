package com.kleetus.trackanything;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTrackerFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_tracker, parent, false);
    }


    public void saveTracker() {

        String trackerName = ((EditText)getActivity().findViewById(R.id.edit_tracker_nane)).getText().toString();
        String kvpName = ((EditText)getActivity().findViewById(R.id.edit_kvp_label)).getText().toString();

        String types = ((Spinner)getActivity().findViewById(R.id.types_spinner)).getSelectedItem().toString();
        String colors = ((Spinner)getActivity().findViewById(R.id.colors_spinner)).getSelectedItem().toString();
        String graph = ((Spinner)getActivity().findViewById(R.id.graph_spinner)).getSelectedItem().toString();

        
    }
}
