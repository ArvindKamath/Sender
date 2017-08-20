package GeneralUtilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a20553.sender.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SmsUtililties.SimUtil;

/**
 * Created by a20553 on 12-08-2017.
 */

public class PermissionHandler {

    final static String TAG = "Permission Handler";

    public static boolean checkPermission(Context ctx, PermissionType permissionType) {
        String manifest = permissionType.getManifest();
        boolean result = false;

        if (manifest.isEmpty()) {
            Toast.makeText(ctx, "Permission not granted", Toast.LENGTH_LONG).show();
        } else if (ContextCompat.checkSelfPermission(ctx, manifest) == PackageManager.PERMISSION_GRANTED) {
            result = true;
        }

        return result;
    }

    private static boolean checkAndRequestPermission(Context ctx,
                                                    AppCompatActivity obj,
                                                    PermissionType permissionType) {

        String manifest = permissionType.getManifest();
        boolean result = false;

        if (manifest.isEmpty()) {
            Log.e(TAG, "unable to get Manifest name for the job: " +
                    permissionType.toString());
            return result;
        }

        if (ContextCompat.checkSelfPermission(ctx, manifest) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(obj,
                        new String[]{manifest},
                        permissionType.getCode());
        } else {
            result = true;
        }

        return result;
    }

    public static boolean isPermissionGranted(Context ctx,
                                              int[] grantResults) {

        boolean result = false;
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            result = true;
        } else {
            Toast.makeText(ctx, "Permission not granted", Toast.LENGTH_LONG).show();
        }

        return result;
    }

    public static void setupAllPermissions(Context ctx,
                                    AppCompatActivity obj) {
        ArrayList<String> permissionTypes = new ArrayList<String>();

        for (PermissionType permissionType: PermissionType.values()) {
            permissionTypes.add(permissionType.getManifest());
        }

        ActivityCompat.requestPermissions(obj,
                permissionTypes.toArray(new String[permissionTypes.size()]),
                0);
    }

    public static void handlePermissionResults(Context ctx, int[] grantResults) {
        if (isPermissionGranted(ctx, grantResults) == false) {
            // TODO how to indicate permission failed
        };
    }
}
