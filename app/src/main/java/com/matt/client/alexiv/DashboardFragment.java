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
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

public class DashboardFragment extends Fragment {

    private MqttAndroidClient client;
    private String TAG = "MainActivity";

    private EditText textMessage, topicMessage, subscribeTopic, unSubscribeTopic;
    private Button publishMessage, subscribe, unSubscribe;
    private PahoMqttClient pahoMqttClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_dashboard, null);
        pahoMqttClient = new PahoMqttClient();

        textMessage = (EditText) v.findViewById(R.id.textMessage);
        topicMessage = (EditText) v.findViewById(R.id.topicMessage);
        publishMessage = (Button) v.findViewById(R.id.publishMessage);

        subscribe = (Button) v.findViewById(R.id.subscribe);
        unSubscribe = (Button) v.findViewById(R.id.unSubscribe);

        subscribeTopic = (EditText) v.findViewById(R.id.subscribeTopic);
        unSubscribeTopic = (EditText) v.findViewById(R.id.unSubscribeTopic);

        final Context context = (Context) getActivity();
        final String leavemsg = getString(R.string.leave_msg);
        final String leavetpc = getString(R.string.leave_tpc);
        final String leavemsgtpc = getString(R.string.leave_msg_tpc);
        final String msgpublished = getString(R.string.msg_published);
        //final Context activity = context.getApplicationContext();
        //Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();

        client = pahoMqttClient.getMqttClient(getContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        publishMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = textMessage.getText().toString().trim();
                String topic = textMessage.getText().toString().trim();
                switch (textMessage.getText().toString()) {
                    case (""):
                        switch (topicMessage.getText().toString()) {
                            case(""):
                                Toast.makeText(context, leavemsgtpc, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(context, leavemsg, Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    default:
                        switch (topicMessage.getText().toString()) {
                            case(""):
                                Toast.makeText(context, leavetpc, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(context, msgpublished, Toast.LENGTH_LONG).show();
                                if (!msg.isEmpty()) {
                                    try {
                                        pahoMqttClient.publishMessage(client, msg, 1, topic);
                                    } catch (MqttException e) {
                                        e.printStackTrace();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        break;
                }
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = subscribeTopic.getText().toString().trim();
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.subscribe(client, topic, 1);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        unSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = unSubscribeTopic.getText().toString().trim();
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.unSubscribe(client, topic);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return v;





    }
}
