package com.example.a20553.sender;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.a20553.sender.TypeOfEntryInFlow.HOW;
import static com.example.a20553.sender.TypeOfEntryInFlow.TOWHOM;
import static com.example.a20553.sender.TypeOfEntryInFlow.WHAT;

public class AddFlowActivity extends AppCompatActivity {

    @BindView(R.id.WhatFlowButton) Button what;
    @BindView(R.id.HowFlowButton) Button how;
    @BindView(R.id.ToWhomFlowButton) Button toWhom;
    @BindView(R.id.WhatText) TextView whatText;
    @BindView(R.id.HowText) TextView howText;
    @BindView(R.id.ToWhomText) TextView toWhomText;
    @BindView(R.id.FlowSave) Button save;
    @BindView(R.id.FlowCancel) Button cancel;
    @BindView(R.id.displayName) EditText displayName;

    private static final int RESULT_PICK_CONTACT = 0xff;
    private String selectedAlert = "Choice not made yet";
    private SenderFlow flow = new SenderFlow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.FlowCancel)
    public void cancelFlow() {
        finish();
    }

    @OnClick(R.id.FlowSave)
    public void saveFlow () {

        flow.setDisplayName(displayName.getText().toString());
        FlowDb flowDb = new FlowDb(this.getApplicationContext());

        flowDb.setFlow(flow);

        finish();
    }

    @OnClick(R.id.WhatFlowButton)
    public void goToWhatActivity() {
        setupFlowAlert(WHAT, whatText);
        flow.setWhatFlowInformation(whatText.getText().toString());
    }

    @OnClick(R.id.HowFlowButton)
    public void goToHowActivity() {
        setupFlowAlert(HOW, howText);
        flow.setHowFlowInformation(howText.getText().toString());
        //SenderFlowConfigurationAlert senderFlowConfigurationAlert =
        //        new SenderFlowConfigurationAlert(this, HOW, howText);
    }

    @OnClick(R.id.ToWhomFlowButton)
    public void goToToWhomActivity() {

        setupFlowAlert(TOWHOM, toWhomText);
    }

    private void addFlow() {
        SenderFlow senderFlow = new SenderFlow(
                displayName.getText().toString(),
                whatText.getText().toString(),
                howText.getText().toString(),
                toWhom.getText().toString());

        FlowDb flowDb = new FlowDb(this.getApplicationContext());

        flowDb.setFlow(senderFlow);
    }

    private boolean isFlowReady() {
        return false;
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
            toWhomText.setText(phoneNo);
            flow.setToWhomFlowInformation(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFlowAlert(final TypeOfEntryInFlow type, final TextView parentText) {

        final int addFlowChoiceId;

        switch (type) {
            case WHAT:
                addFlowChoiceId = R.array.WhatChoices;
                break;
            case HOW:
                addFlowChoiceId = R.array.HowChoices;
                break;
            case TOWHOM:
                addFlowChoiceId = R.array.ToWhomChoices;
                break;
            default:
                // TODO decide error handling
                addFlowChoiceId = R.array.WhatChoices;
                break;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose Wisely");

        builder.setItems(addFlowChoiceId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] stringList = getResources().getStringArray(addFlowChoiceId);
                selectedAlert = stringList[which];

                parentText.setText(selectedAlert);

                if ((type == TOWHOM) && (selectedAlert.equals("Contact"))){
                    pickAContact();
                }
            }
        });

        builder.show();
    }

    private void pickAContact() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);

        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }
}
