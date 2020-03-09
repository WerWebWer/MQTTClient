package com.matt.client.alexiv;

import android.content.Context;
import android.util.Log;

import com.matt.client.alexiv.Constants;
import com.matt.client.alexiv.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.UnsupportedEncodingException;

public class UsagesServer {

    private PahoMqttClient pahoMqttClient;
    private MqttAndroidClient client;

    public void onCreate(Context context){
        if (context == null ) Log.d("UsagesServer", "context is null");
        pahoMqttClient = new PahoMqttClient();
        client = pahoMqttClient.getMqttClient(context, Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

    }

    public void Subscribe(String topic) {
        try {
            pahoMqttClient.subscribe(client, topic, 1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void UnSubscribe(String topic) {
        try {
            pahoMqttClient.unSubscribe(client, topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void PublishMessage(String topic, String msg, Integer qos) {
        try {
            pahoMqttClient.publishMessage(client, msg, qos, topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
