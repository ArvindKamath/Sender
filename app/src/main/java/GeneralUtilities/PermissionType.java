package GeneralUtilities;

import android.Manifest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a20553 on 12-08-2017.
 */

public enum PermissionType {
    PERMISSION_TYPES_READ_PHONE_STATE (1, Manifest.permission.READ_PHONE_STATE),
    PERMISSION_TYPES_SEND_SMS (2, Manifest.permission.SEND_SMS),
    PERMISSION_TYPES_GET_LOCATION (3, Manifest.permission.ACCESS_FINE_LOCATION);

    private PermissionType(int code, String manifest) {
        mCode = code;
        mManifest = manifest;
    }

    final public String getManifest() {
        return mManifest;
    }

    final public int getCode() { return mCode; }

    final private String mManifest;
    final private int mCode;
}
