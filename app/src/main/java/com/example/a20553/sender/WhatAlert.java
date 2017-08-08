package com.example.a20553.sender;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import static com.example.a20553.sender.typeOfEntryInFlow.HOW;
import static com.example.a20553.sender.typeOfEntryInFlow.TOWHOM;
import static com.example.a20553.sender.typeOfEntryInFlow.WHAT;

/**
 * Created by a20553 on 08-08-2017.
 */

public class WhatAlert {

    private String selectedAlert = "Choice not made yet";

    public WhatAlert(AddFlowActivity activity,
                     typeOfEntryInFlow type,
                     final TextView parentText) {

        final CharSequence[] addFlowChoice;
        if (type == WHAT) {
           addFlowChoice = new CharSequence[]{"Send Location", "Send Text Message"};
        } else if (type == HOW) {
            addFlowChoice = new CharSequence[]{"WhatsApp", "SMS", "Other"};
        } else  {
            addFlowChoice = new CharSequence[]{"Come", "Back", "Later"};
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Choose...");

        builder.setItems(addFlowChoice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedAlert = addFlowChoice[which].toString();

                parentText.setText(selectedAlert.toString());
            }
        });

        builder.show();
    }

    public String getSelectedAlert() {
        return selectedAlert;
    }
}
