package com.drac_android.infonepal.Utilities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Utils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDeviceId(Context c) {


        String device_id= "asdsadasdasds";

        return device_id;
    }


    public static Location getLocation(Context context) {
        LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mgr.getAllProviders();
        if (providers != null && providers.contains(LocationManager.NETWORK_PROVIDER)) {

            Location loc = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return loc;
        }else return null;
    }


    public static String getCurTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
