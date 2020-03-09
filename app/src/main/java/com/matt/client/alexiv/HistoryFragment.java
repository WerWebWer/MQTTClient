package com.matt.client.alexiv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;


public class HistoryFragment extends Fragment{

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fh = inflater.inflate(R.layout.fragment_history, null);
/*        client = ((MainActivity) getActivity()).getClient();
        pahoMqttClient = ((MainActivity) getActivity()).getPahoMqttClient();*/







        final Switch switch1 = (Switch) fh.findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(),"TURN ON",Toast.LENGTH_SHORT).show();
                        //pahoMqttClient.publishMessage(client, "ON", 1, "Light");
                    } catch (Exception e) {
                        switch1.setChecked(false);
                        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(),"You have problem",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(),"TURN OFF",Toast.LENGTH_SHORT).show();
                        //pahoMqttClient.publishMessage(client, "OFF", 1, "Light");
                    } catch (Exception e) {
                        switch1.setChecked(true);
                        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(),"You have problem",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        return fh;
    }
}
