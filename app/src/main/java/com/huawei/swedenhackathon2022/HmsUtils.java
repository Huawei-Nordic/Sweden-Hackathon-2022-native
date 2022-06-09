package com.huawei.swedenhackathon2022;

import android.content.Context;
import android.util.Log;

import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;

public class HmsUtils {

    final static String TAG = "HMS Utility";

    public static boolean isHmsAvailable(Context context) {
        boolean isAvailable = false;
        if (null != context) {
            int result = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context);
            isAvailable = (com.huawei.hms.api.ConnectionResult.SUCCESS == result);
        }
        Log.i(TAG, "isHmsAvailable: " + isAvailable);
        return isAvailable;
    }


    public static String getPushNotificationsToken(Context context) throws ApiException {
        //String appId = AGConnectInstance.getInstance().getOptions().getString("106430067");
        return HmsInstanceId.getInstance(context).getToken("106430067", "HCM");
    }
}
