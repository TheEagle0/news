package com.example.theeagle.news.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.net.NetworkInfo.State.CONNECTED;

public class NetworkUtils {
    public static boolean isConnected(Application app) {
        final ConnectivityManager cm = (ConnectivityManager) app.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && (cm.getNetworkInfo(TYPE_MOBILE).getState() == CONNECTED
                || cm.getNetworkInfo(TYPE_WIFI).getState() == CONNECTED);
    }
}
