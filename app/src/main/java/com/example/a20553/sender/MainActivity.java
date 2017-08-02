package com.example.a20553.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";

    @BindView(R.id.main_welcome_text) TextView text;
    @BindView(R.id.main_settings_button) Button settings;
    @BindView(R.id.main_send_message_button) Button sendMessage;
    @BindView(R.id.main_send_location_button) Button sendLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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

    @OnClick(R.id.main_send_message_button)
    public void sendMessage() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        //sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }
}
