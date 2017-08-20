package com.example.a20553.sender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import GeneralUtilities.PermissionHandler;
import GeneralUtilities.PermissionType;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpsActivity extends AppCompatActivity {

    private static final String TAG = "GpsActivity";

    @BindView(R.id.gps_back_button)
    Button gps_back_button;

    @BindView(R.id.gps_welcome_text)
    TextView text;

    private FusedLocationProviderClient mFusedLocationClient;

    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_location);

        ButterKnife.bind(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (PermissionHandler.checkPermission(this.getApplicationContext(),
                PermissionType.PERMISSION_TYPES_GET_LOCATION) == true) {
            getLastLocation();
        }
    }

    @OnClick(R.id.gps_back_button)
    public void goBack() {
        finish();
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     * <p>
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();

                            text.setText("Sending Location");

                            if (mLastLocation != null) {
                                String locationMessage = getGpsMessage(mLastLocation);

                                sendMessage(locationMessage);

                                goBack();
                            }
                        } else {
                            Log.w(TAG, "getLastLocation:exception", task.getException());
                        }
                    }
                });
    }

    private String getGpsMessage(Location mLastLocation) {
        return getResources().getString(R.string.gps_text_prefix) +
                " " +
                getResources().getString(R.string.gps_url_base) +
                mLastLocation.getLatitude() +
                "," +
                mLastLocation.getLongitude();
    }

    public void sendMessage(String message) {
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");

            String smsNumber = "919845395823";

            if (!smsNumber.isEmpty()) {
                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}