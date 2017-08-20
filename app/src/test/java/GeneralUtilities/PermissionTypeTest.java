package GeneralUtilities;

import android.Manifest;

import org.junit.Test;

import java.security.Permission;

import static GeneralUtilities.PermissionType.PERMISSION_TYPES_GET_LOCATION;
import static GeneralUtilities.PermissionType.PERMISSION_TYPES_READ_PHONE_STATE;
import static GeneralUtilities.PermissionType.PERMISSION_TYPES_SEND_SMS;
import static org.junit.Assert.*;

/**
 * Created by a20553 on 13-08-2017.
 */
public class PermissionTypeTest {

    @Test
    public void permissionType_BasicValidation() {
        assertEquals(PermissionType.PERMISSION_TYPES_READ_PHONE_STATE.getManifest(), Manifest.permission.READ_PHONE_STATE);
        assertEquals(PermissionType.PERMISSION_TYPES_SEND_SMS.getManifest(), Manifest.permission.SEND_SMS);
        assertEquals(PermissionType.PERMISSION_TYPES_GET_LOCATION.getManifest(), Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Test
    public void permissionType_objectValidation() {

        PermissionType permissionType = PERMISSION_TYPES_READ_PHONE_STATE;
        assertEquals(permissionType.getManifest(), Manifest.permission.READ_PHONE_STATE);

        permissionType = PERMISSION_TYPES_SEND_SMS;
        assertEquals(permissionType.getManifest(), Manifest.permission.SEND_SMS);

        permissionType = PERMISSION_TYPES_GET_LOCATION;
        assertEquals(permissionType.getManifest(), Manifest.permission.ACCESS_FINE_LOCATION);
    }
}