package com.example.a20553.sender;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import static com.example.a20553.sender.TypeOfEntryInFlow.HOW;
import static com.example.a20553.sender.TypeOfEntryInFlow.WHAT;

/**
 * Created by a20553 on 08-08-2017.
 */

public class SenderFlowConfigurationAlert {

    private String selectedAlert = "Choice not made yet";

    public SenderFlowConfigurationAlert(final AddFlowActivity activity,
                                        TypeOfEntryInFlow type,
                                        final TextView parentText) {

        final int addFlowChoiceId ;

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

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Choose...");

        builder.setItems(addFlowChoiceId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] stringList = activity.getResources().getStringArray(addFlowChoiceId);
                selectedAlert = stringList[which];

                parentText.setText(selectedAlert);
            }
        });

        builder.show();
    }

    public String getSelectedAlert() {
        return selectedAlert;
    }
}
