package com.matt.client.alexiv;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class MainActivity extends AppCompatActivity {

    private HistoryFragment frag1;
    private DashboardFragment frag2;
    private SettingsFragment frag3;
    private FragmentTransaction fTrans;

    private PahoMqttClient pahoMqttClient;
    private MqttAndroidClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pahoMqttClient = new PahoMqttClient();
        client = new MqttAndroidClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
        frag1 = new HistoryFragment();
        frag2 = new DashboardFragment();
        frag3 = new SettingsFragment();

        Toast.makeText(getApplicationContext(),"Hello, my master!",Toast.LENGTH_LONG).show();
        //client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.main_container, frag1);
        fTrans.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public PahoMqttClient getPahoMqttClient(){
        return pahoMqttClient;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_history:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.main_container, frag1);
                    fTrans.commit();
                    Log.d("Click", "History");
                    return true;
                case R.id.navigation_dashboard:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.main_container, frag2);
                    fTrans.commit();
//                    DashboardFragment dashboardFragment = new DashboardFragment();
//                    FragmentManager manager = getSupportFragmentManager();
//                    manager.beginTransaction().replace(R.id.main_container, frag2).commit();
                    Log.d("Click", "Dashboard");
                    return true;
                case R.id.navigation_settings:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.main_container, frag3);
                    fTrans.commit();
                    Log.d("Click", "Settings");
                    return true;
                default:
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.add(R.id.main_container, frag1);
                    fTrans.commit();
                    break;
            }
            return false;
        }
    };
}
