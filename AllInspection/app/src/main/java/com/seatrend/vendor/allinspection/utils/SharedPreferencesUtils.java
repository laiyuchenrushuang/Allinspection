package com.seatrend.vendor.allinspection.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import com.seatrend.vendor.allinspection.base.Constants;
import com.seatrend.vendor.allinspection.MyApplication;

import java.util.Objects;



@TargetApi(Build.VERSION_CODES.KITKAT)
public class SharedPreferencesUtils {
    public static void setIsFirst(boolean isfirst){
        Objects.requireNonNull(MyApplication.Companion.getMyApplicationContext()).getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putBoolean(Constants.Companion.getIS_FIRST(),isfirst).apply();
    }

    public static boolean getIsFirst(){
        return Objects.requireNonNull(MyApplication.Companion.getMyApplicationContext()).getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getBoolean(Constants.Companion.getIS_FIRST(), true);
    }
    public static String getNetworkAddress(){
        return   Objects.requireNonNull(MyApplication.Companion.getMyApplicationContext()).getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .getString(Constants.Companion.getNET_K(),"http://192.168.0.221:8099/ddc");
    }
    public static void setNetworkAddress(String network){
        Objects.requireNonNull(MyApplication.Companion.getMyApplicationContext()).getSharedPreferences(Constants.Companion.getSETTING(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getNET_K(),network).apply();
    }
}
