package com.seatrend.environment.inspection;

import android.app.Application;
import android.content.Context;

/**
 * Created by ly on 2020/5/13 15:06
 */
public class MyApplication extends Application {
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }
}
