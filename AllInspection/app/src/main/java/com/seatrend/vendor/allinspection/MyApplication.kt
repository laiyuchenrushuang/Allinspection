package com.seatrend.vendor.allinspection

import android.app.Application

/**
 * Created by ly on 2020/4/8 9:40
 */
class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()


    }

    companion object {


        var myApplicationContext: MyApplication? = null

    }
}