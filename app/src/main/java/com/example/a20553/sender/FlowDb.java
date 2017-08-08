package com.example.a20553.sender;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by a20553 on 06-08-2017.
 */

public class FlowDb {

    /** This application's preferences label */
    private static final String PREFS_NAME = "com.example.a20553.sender.Flows";

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;

    private int numberOfFlows;
    private Map<String, ?> flows;

    /** Method to return a unique key for any field belonging to a given object
     * @param id of the object
     * @param fieldKey of a particular field belonging to that object
     * @return key String uniquely identifying the object's field
     */
    private String getFieldKey(int id, String fieldKey) {
        return  KEY_PREFIX + id + "_" + fieldKey;
    }

    /** generic field keys */
    private static final String KEY_USERNAME = "com.example.a20553.sender.KEY_USERNAME";

    /** The prefix for flattened user keys */
    public static final String KEY_PREFIX = "com.example.a20553.sender.KEY";

    /** Constructor takes an android.content.Context argument*/
    public FlowDb(Context ctx){
        if(settings == null){
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }
       /*
        * Get a SharedPreferences editor instance.
        * SharedPreferences ensures that updates are atomic
        * and non-concurrent
        */
        editor = settings.edit();

        setupFlowInformation();
    }

    /** Store or Update */
    public void setUser(SenderFlow flow){
        if(flow == null)
            return; // don't bother

        int id = flow.getId();
        editor.putString(
                getFieldKey(id, KEY_USERNAME),
                flow.getDisplayName());
        editor.commit();
    }

    /** Retrieve */
    public SenderFlow getUser(int id){
        String name = settings.getString(
                getFieldKey(id, KEY_USERNAME),
                "" ); // default value

        return new SenderFlow(id, name);
    }

    /** Delete */
    public void deleteUser(SenderFlow user){
        if(user == null)
            return; // don't bother

        int id = user.getId();
        editor.remove( getFieldKey(id, KEY_USERNAME) );

        editor.commit();
    }

    public int getNumberOfFlows() {
        return numberOfFlows;
    }

    public Map<String, ?> getFlows() {
        return flows;
    }

    private void setupFlowInformation () {
        flows = settings.getAll();
        numberOfFlows = flows.size();
    }
}