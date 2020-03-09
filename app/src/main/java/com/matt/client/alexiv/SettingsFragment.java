package com.matt.client.alexiv;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private EditText textClientID, textServer, textPort;
    private Button saveSettings;
    //public Constants constants;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_settings, null);


        //textClientID = (EditText) v.findViewById(R.id.clientIDTextView);
        //textServer = (EditText) v.findViewById(R.id.serverTextView);
        //textPort = (EditText) v.findViewById(R.id.portTextView);
        //textPort.setHint(CLIENT_ID);

        //saveSettings = (Button) v.findViewById(R.id.saveSettings);

//        saveSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });

        return v;
    }
}
