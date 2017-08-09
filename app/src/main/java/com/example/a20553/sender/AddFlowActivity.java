package com.example.a20553.sender;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private static final int RESULT_PICK_CONTACT = 0xff;

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
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews
            toWhomText.setText(name + " : " + phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
