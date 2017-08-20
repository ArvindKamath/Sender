package com.example.a20553.sender;

/**
 * Created by a20553 on 30-07-2017.
 */

public class SenderFlow {

    static int nextFreeId = 1;
    public static final int getNumberOfMembers = 4;

    private String displayName;
    private String whatFlowInformation;
    private String howFlowInformation;
    private String toWhomFlowInformation;

    public SenderFlow() {
        setDisplayName("Default FlowId");
    }

    public SenderFlow(String displayName,
                      String whatFlowInformation,
                      String howFlowInformation,
                      String toWhomFlowInformation) {
        this.displayName = displayName;
        this.whatFlowInformation = whatFlowInformation;
        this.howFlowInformation = howFlowInformation;
        this.toWhomFlowInformation = toWhomFlowInformation;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isFlowReadyToStore() {

        return false;
    }

    public String getWhatFlowInformation() {
        return whatFlowInformation;
    }

    public void setWhatFlowInformation(String whatFlowInformation) {
        this.whatFlowInformation = whatFlowInformation;
    }

    public String getHowFlowInformation() {
        return howFlowInformation;
    }

    public void setHowFlowInformation(String howFlowInformation) {
        this.howFlowInformation = howFlowInformation;
    }

    public String getToWhomFlowInformation() {
        return toWhomFlowInformation;
    }

    public void setToWhomFlowInformation(String toWhomFlowInformation) {
        this.toWhomFlowInformation = toWhomFlowInformation;
    }
}
