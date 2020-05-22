package com.seatrend.vendor.allinspection

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDexApplication

/**
 * Created by ly on 2020/4/8 9:40
 */
class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
    }

    companion object {
        var myApplicationContext: Context? = null
    }
}