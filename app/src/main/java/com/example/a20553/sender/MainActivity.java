package com.example.a20553.sender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
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
    @BindView(R.id.sendLocation) Button sendLocationButton;
    @BindView(R.id.ViewFlow) Button viewFlow;
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

        viewFlow();
    }


    @Override
    protected void onResume() {
        super.onResume();
        removeButtons();

        viewFlow();
    }

    @OnClick(R.id.ViewFlow)
    public void viewFlow() {
        FlowDb flowDb = new FlowDb(this.getApplicationContext());

        List<SenderFlow> senderFlows = flowDb.getAllFlows();
        List<String> string = new ArrayList<String>();

        for (SenderFlow flow: senderFlows) {
            string.add(flow.getDisplayName() + ":" +
            flow.getWhatFlowInformation() + ":" +
            flow.getHowFlowInformation() + ":" +
            flow.getToWhomFlowInformation() + "<>");
        }

        removeButtons();
        setupButtons(senderFlows);
    }

    @OnClick (R.id.sendLocation)
    public void sendLocation() {
        Intent intent = new Intent(this, GpsActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.Clear)
    public void clearFlowDb() {
        FlowDb flowDb = new FlowDb(this.getApplicationContext());
        flowDb.clearAllFlows();

        viewFlow();
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

    private void setupButtons(List<SenderFlow> senderFlows) {
        GridLayout layout = (GridLayout) findViewById(R.id.FlowButtonsLayout);
        layout.setRowCount(4);
        layout.setColumnCount(2);
        int i = 0;
        {
            final Button myButton = new Button(this);
            myButton.setText("+");
            myButton.setId(i++);

            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    addFlow();
                }
            });
        }

        for (SenderFlow flow: senderFlows) {
            Button myButton = new Button(this);
            myButton.setText(flow.getDisplayName());
            myButton.setId(i++);
            final int id_ = myButton.getId();
            
            layout.addView(myButton);

            final String string = myButton.getText().toString();
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    text.setText("Touched " + string + " button");
                }
            });
        }
    }

    private void removeButtons() {
        GridLayout layout = (GridLayout) findViewById(R.id.FlowButtonsLayout);
        layout.removeAllViews();
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

    public void addFlow (){
        Intent intent = new Intent(this, AddFlowActivity.class);
        startActivity(intent);
    }
}