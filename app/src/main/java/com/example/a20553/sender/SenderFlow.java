package com.example.a20553.sender;

/**
 * Created by a20553 on 30-07-2017.
 */

public class SenderFlow {

    static int nextFreeId = 1;

    private int id;
    private String displayName;

    private WhatActivity whatActivity;
    private HowActivity howActivity;
    private ToWhomActivity toWhomActivity;


    public SenderFlow() {
        setId(nextFreeId);
    }

    public SenderFlow(int id, String name) {
        setId(id);
        setDisplayName(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private int getNewId() {
        return 10;
    }
}
