package com.example.a20553.sender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpsActivity extends AppCompatActivity {
    @BindView(R.id.gps_back_button)
    Button gps_back_button;

    @BindView(R.id.gps_welcome_text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_location);

        ButterKnife.bind(this);

        GpsTracker gps = new GpsTracker();
    }

    @OnClick(R.id.gps_back_button)
    public void goBack() {
        finish();
    }
}
