package SmsUtililties;

/**
 * Created by a20553 on 10-08-2017.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import GeneralUtilities.PermissionHandler;
import GeneralUtilities.PermissionType;

public class SimUtil {

    final static String TAG = "SimUtil";
    final static int SIM1 = 0;
    final static int SIM2 = 1;

    static int numOfSims = 0;
    public static boolean simInformationSetup = false;
    private List<String> namesOfSim = new ArrayList<String>();

    public static SimUtil simUtilInstance = null;

    public static SimUtil getSimUtilInstance(Context ctx, AppCompatActivity obj) {
        if (simUtilInstance == null) {
            simUtilInstance = new SimUtil(ctx, obj);
        }

        return simUtilInstance;
    };

    public SimUtil(Context ctx, AppCompatActivity obj) {
        if (simInformationSetup == false) {
            if (PermissionHandler.checkPermission(ctx,
                    PermissionType.PERMISSION_TYPES_READ_PHONE_STATE) == true) {
                setupSimInformation(ctx);
            }
        }
    }

    private void setupSimInformation(Context ctx) {
        SubscriptionManager mSubscriptionManager = SubscriptionManager.from(ctx);
        List<SubscriptionInfo> subscriptions = mSubscriptionManager.getActiveSubscriptionInfoList();

        for (SubscriptionInfo subscriptionInfo : subscriptions) {
            if (subscriptionInfo.getDisplayName().toString().isEmpty() == false) {
                numOfSims++;
                namesOfSim.add((numOfSims - 1), subscriptionInfo.getDisplayName().toString());
            }
        }
    }

    public boolean isDualSimPhone() {
        return numOfSims > 1;
    }

    public String getNameOfSim1() {
        return namesOfSim.get(SIM1);
    }

    public String getNameOfSim2() {
        if (isDualSimPhone() == false) {
            Log.e(TAG, "Error, NOT a dual sim phone");

            // TODO: Error handling? lets return the SIM1 name for now
            return namesOfSim.get(SIM1);
        }

        return namesOfSim.get(SIM2);
    }

    // Left for additional debug information
    public void getSimInformation(Context ctx) {
        String simInformation;

        SubscriptionManager mSubscriptionManager = SubscriptionManager.from(ctx);
        List<SubscriptionInfo> subscriptions = mSubscriptionManager.getActiveSubscriptionInfoList();

        for (SubscriptionInfo subscriptionInfo: subscriptions) {
            simInformation = "Subscription Information: " +
                    subscriptionInfo.getCountryIso().toString() + ":" +
                    subscriptionInfo.getDisplayName().toString() + ":" +
                    subscriptionInfo.getSimSlotIndex() + ":" +
                    String.valueOf(subscriptionInfo.getSubscriptionId());

            Toast.makeText(ctx, simInformation, Toast.LENGTH_LONG).show();
        }
    }
}