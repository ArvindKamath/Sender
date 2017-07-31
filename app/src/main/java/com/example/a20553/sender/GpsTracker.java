package com.example.a20553.sender;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by a20553 on 31-07-2017.
 */

public class GpsTracker extends Service implements LocationListener {

    String TAG = "GpsTracker";

    LocationManager locationManager;

    // TODO This constructor is not working
    public void GpsTracker() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> stringList = locationManager.getAllProviders();

        for(int i = 0; i < stringList.size(); i++) {
            Log.d(TAG, stringList.get(i));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}