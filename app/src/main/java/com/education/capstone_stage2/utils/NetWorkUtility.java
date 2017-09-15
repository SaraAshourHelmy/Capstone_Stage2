package com.education.capstone_stage2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;


public class NetWorkUtility {

    public static boolean checkInternetConnection(Context context) {
        Log.e("internet_connection", "enter");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.isConnected() &&
                        (ni.getTypeName().equalsIgnoreCase("MOBILE")
                                || ni.getTypeName().equalsIgnoreCase("WIFI")))
                    return true;
            }
        } else {
            Network[] networks = cm.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = cm.getNetworkInfo(mNetwork);
                if (networkInfo != null &&
                        (networkInfo.getTypeName().equalsIgnoreCase("WIFI")
                                || networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                        && networkInfo.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

}