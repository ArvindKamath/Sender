package com.example.a20553.sender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import GeneralUtilities.PermissionHandler;
import GeneralUtilities.PermissionType;
import SmsUtililties.SimUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE = 1;
    String phoneNo;
    String message;

    @BindView(R.id.loggerText) TextView text;
    @BindView(R.id.AddFlow) Button button;
    @BindView(R.id.sendLocation) Button sendLocationButton;
    @BindView(R.id.ViewFlow) Button viewFlow;
    @BindView(R.id.FlowInfo) TextView flowInfo;
    @BindView(R.id.Clear) Button clear;
    @BindView(R.id.SendTextMessage) Button sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupActivityView();

        PermissionHandler.setupAllPermissions(this.getApplicationContext(),
                this);
    }

    @OnClick (R.id.AddFlow)
    public void addFlow (){
        Intent intent = new Intent(this, AddFlowActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ViewFlow)
    public void viewFlow() {
        FlowDb flowDb = new FlowDb(this.getApplicationContext());

        SenderFlow senderFlow = flowDb.getFirstFlow();

        flowInfo.setText(senderFlow.getDisplayName() +
                senderFlow.getWhatFlowInformation() +
                senderFlow.getHowFlowInformation() +
                senderFlow.getToWhomFlowInformation());
    }

    @OnClick (R.id.sendLocation)
    public void sendLocation() {
        Intent intent = new Intent(this, GpsActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.Clear)
    public void clearFlowDb() {

    }

    @OnClick (R.id.SendTextMessage)
    public void sendSmsMessage() {
        if (PermissionHandler.checkPermission(this.getApplicationContext(),
                PermissionType.PERMISSION_TYPES_SEND_SMS) == true) {
            getSimInformation();
        }
        //getSimInformation();
    }

    public void setupActivityView () {
        FlowDb flowDb = new FlowDb(this.getApplicationContext());
        int numberOfFlows = flowDb.getNumberOfFlows();

        text.setText("Number of Activities: " + numberOfFlows);
    }

    private void setupButton(int num) {
        for (int i = 0; i < num; i++) {
            Button myButton = new Button(this);
            myButton.setText("Button :" + i);
            myButton.setId(i);
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) findViewById(R.id.myDynamicLayout);
            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    text.setText("Touched this button");
                }
            });
        }
    }

    protected void sendSMSMessage() {
        phoneNo = "+919845395823";
        message = "Hello, testing one two three";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    protected void getSimInformation() {
        SimUtil.getSimUtilInstance(this.getApplicationContext(), this)
                .getSimInformation(this.getApplicationContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionHandler.handlePermissionResults(this.getApplicationContext(), grantResults);
    }
}