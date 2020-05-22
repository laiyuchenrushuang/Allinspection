package com.seatrend.vendor.allinspection.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import com.seatrend.vendor.allinspection.base.Constants;
import com.seatrend.vendor.allinspection.MyApplication;
import com.seatrend.vendor.allinspection.entity.CameSpinner;
import com.seatrend.vendor.allinspection.entity.ShareEntity;

import java.util.ArrayList;
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

    /**
     * 写入环检照片spinner
     */
    public static void setHJCameSpinerList(String unqualifedList){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSHARE_PRE_KEY(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getE_INSPECTION_PHOTO(),unqualifedList).commit();
    }

    /**
     * 获取环检照片spinner
     * @return UserInfo
     */
    public static ArrayList<CameSpinner> getHJCameSpinerList(){
        String data= MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSHARE_PRE_KEY(),Context.MODE_PRIVATE).
                getString(Constants.Companion.getE_INSPECTION_PHOTO(),"");
        if(StringUtils.isNull(data).equals("")){
            return null;
        }else return GsonUtils.jsonToArrayList(data,CameSpinner.class);
    }
    /**
     * 写入all照片spinner
     */
    public static void setAllCameSpinerList(String unqualifedList){
        MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSHARE_PRE_KEY(), Context.MODE_PRIVATE)
                .edit().putString(Constants.Companion.getALL_PHOTO(),unqualifedList).commit();
    }

    /**
     * 获取all照片spinner
     * @return UserInfo
     */
    public static ArrayList<ShareEntity.PhotoListBean> getAllCameSpinerList(){
        String data= MyApplication.Companion.getMyApplicationContext().getSharedPreferences(Constants.Companion.getSHARE_PRE_KEY(),Context.MODE_PRIVATE).
                getString(Constants.Companion.getALL_PHOTO(),"");
        if(StringUtils.isNull(data).equals("")){
            return null;
        }else return GsonUtils.jsonToArrayList(data, ShareEntity.PhotoListBean.class);
    }
}
