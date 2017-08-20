package com.example.a20553.sender;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by a20553 on 06-08-2017.
 */

public class FlowDb {

    private final String TAG = "FlowDb";

    /** This application's preferences label */
    private static final String PREFS_NAME = "com.example.a20553.sender.Flows";
    private static final String KEY_DISPLAY_NAME = "display_name";
    private static final String KEY_WHAT_ACTION = "flow_what";
    private static final String KEY_HOW_ACTION = "flow_how";
    private static final String KEY_TOWHOM_ACTION = "flow_to_whom";
    private static final String KEY_SENDER_FLOW_NAMES = "SENDER_FLOW_NAMES";
    private static final String KEY_DEFAULT = "FLOW_DEFAULT";

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;

    private int numberOfFlows;
    private Map<String, ?> flows;

    /** Method to return a unique key for any field belonging to a given object
     * @param stringId of the object
     * @param fieldKey of a particular field belonging to that object
     * @return key String uniquely identifying the object's field
     */
    private String getFieldKey(String stringId, String fieldKey) {
        //return  PREFS_NAME + stringId + "_" + fieldKey;
        return stringId + fieldKey;
    }

    /** generic field keys */
    private static final String KEY_USERNAME = "com.example.a20553.sender.KEY_USERNAME";

    /** The prefix for flattened user keys */
    public static final String KEY_PREFIX = "com.example.a20553.sender.KEY";

    private static JSONObject js;

    /** Constructor takes an android.content.Context argument*/
    public FlowDb(Context ctx){
        if(settings == null){
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }

        setupFlowInformation();

       /*
        * Get a SharedPreferences editor instance.
        * SharedPreferences ensures that updates are atomic
        * and non-concurrent
        */
        editor = settings.edit();

    }

    /** Store or Update */
    public void setFlow(SenderFlow flow){
        if(flow == null)
            return; // don't bother

        editor.putString(
                getFieldKey(flow.getDisplayName(), KEY_DISPLAY_NAME),
                flow.getDisplayName());
        editor.putString(
                getFieldKey(flow.getDisplayName(), KEY_WHAT_ACTION),
                flow.getWhatFlowInformation());
        editor.putString(
                getFieldKey(flow.getDisplayName(), KEY_HOW_ACTION),
                flow.getHowFlowInformation());
        editor.putString(
                getFieldKey(flow.getDisplayName(), KEY_TOWHOM_ACTION),
                flow.getToWhomFlowInformation());
        editor.commit();

        try {
            js.put(flow.getDisplayName(), true);
            editor.putString(KEY_SENDER_FLOW_NAMES, js.toString());
        } catch (Exception e) {
            Log.e(TAG, "Exception in JSON");
        }


    }

    /** Retrieve */
    public SenderFlow getFlow(String displayName){
        String whatAction;
        String howAction;
        String toWhomAction;

        whatAction = settings.getString(
                getFieldKey(displayName, KEY_DISPLAY_NAME), KEY_DEFAULT);
        howAction = settings.getString(
                getFieldKey(displayName, KEY_DISPLAY_NAME), KEY_DEFAULT);
        toWhomAction = settings.getString(
                getFieldKey(displayName, KEY_DISPLAY_NAME),KEY_DEFAULT);

        return new SenderFlow(displayName,
                whatAction,
                howAction,
                toWhomAction);
    }

    /** Delete */
    public void deleteFlow(SenderFlow flow){
        if(flow == null)
            return; // don't bother

        editor.remove( getFieldKey(flow.getDisplayName(), KEY_DISPLAY_NAME));
        editor.remove( getFieldKey(flow.getDisplayName(), KEY_WHAT_ACTION));
        editor.remove( getFieldKey(flow.getDisplayName(), KEY_HOW_ACTION));
        editor.remove( getFieldKey(flow.getDisplayName(), KEY_TOWHOM_ACTION));

        editor.commit();
    }

    public int getNumberOfFlows() {
        js.length();
        return numberOfFlows;
    }

    public SenderFlow getFirstFlow() {
        Iterator<String> iter = js.keys();
        // only the first string/kley for now
        return getFlow(iter.toString());
    }

    private void setupFlowInformation () {
        String jsString = settings.getString(KEY_SENDER_FLOW_NAMES, KEY_DEFAULT);

        if (jsString == KEY_DEFAULT)
            js = new JSONObject();
        else
        {
            try {
                js = new JSONObject(jsString);
            } catch (Exception e) {
                Log.e(TAG, "JSON creation exception");
            }
        }
    }
}