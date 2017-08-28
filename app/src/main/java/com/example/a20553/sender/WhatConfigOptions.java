package com.example.a20553.sender;

/**
 * Created by a20553 on 25-08-2017.
 */

public enum WhatConfigOptions {
    WHAT_CONFIG_OPTIONS_SEND_LOCATION(1, "Send Location"),
    WHAT_CONFIG_OPTIONS_SEND_TEXT_MESSAGE(2, "Send Text Message");

    private WhatConfigOptions(int code, String text) {
        mCode = code;
        mDisplayText = text;
    }

    public int getWhatConfigCode() {
        return mCode;
    }

    public String getWhatConfigDisplayText() {
        return mDisplayText;
    }

    private int mCode;
    private String mDisplayText;
    static int length;
}
