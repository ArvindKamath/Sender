package com.example.a20553.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String TAG1 = "Main Activity";

    @BindView(R.id.main_welcome_text) TextView text;
    @BindView(R.id.main_settings_button) Button settings;
    @BindView(R.id.main_send_location_button) Button sendLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.d(TAG1, "onCreate");
    }

    @OnClick (R.id.main_settings_button)
    public void goToSettings (){
        Intent intent = new Intent(this, SenderSettings.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_send_location_button)
    public void goToSendLocation() {
        Intent intent = new Intent(this, GpsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG1, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG1, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG1, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG1, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG1, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG1, "onDestroy");
    }
}
