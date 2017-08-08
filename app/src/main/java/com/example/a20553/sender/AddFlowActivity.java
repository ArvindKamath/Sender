package com.example.a20553.sender;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;import static com.example.a20553.sender.typeOfEntryInFlow.HOW;
import static com.example.a20553.sender.typeOfEntryInFlow.TOWHOM;
import static com.example.a20553.sender.typeOfEntryInFlow.WHAT;

public class AddFlowActivity extends AppCompatActivity {

    @BindView(R.id.WhatFlowButton) Button what;
    @BindView(R.id.HowFlowButton) Button how;
    @BindView(R.id.ToWhomFlowButton) Button toWhom;
    @BindView(R.id.WhatText) TextView whatText;
    @BindView(R.id.HowText) TextView howText;
    @BindView(R.id.ToWhomText) TextView toWhomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);
    }

    private void addFlow() {
        SenderFlow senderFlow = new SenderFlow(9, "Kamath");

        FlowDb flowDb = new FlowDb(this.getApplicationContext());

        flowDb.setUser(senderFlow);
    }

    @OnClick(R.id.WhatFlowButton)
    public void goToWhatActivity() {
        WhatAlert whatAlert = new WhatAlert(this, WHAT, whatText);
    }

    @OnClick(R.id.HowFlowButton)
    public void goToHowActivity() {
        WhatAlert whatAlert = new WhatAlert(this, HOW, howText);
    }

    @OnClick(R.id.ToWhomFlowButton)
    public void goToToWhomActivity() {
        WhatAlert whatAlert = new WhatAlert(this, TOWHOM, toWhomText);
    }
}
