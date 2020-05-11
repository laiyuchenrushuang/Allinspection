package com.seatrend.vendor.allinspection.base

import android.os.Environment

/**
 * Created by ly on 2020/4/8 13:24
 */
class Constants {
    companion object {
        val STORAGE = Environment.getExternalStorageDirectory().toString() + "/AllInspect/"

        val PICTRUE_PATH = STORAGE + "CAMERA_PICTRUE/"
        val VIDEO_PATH = STORAGE + "CAMERA_VIDEO/"
        val FILE_PATH = STORAGE + "FILE_PATH/"


        //sharepreference
        var SETTING = "setting"
        var NET_K = "network"
        var IS_FIRST = "is_first"

        //network
        var HTTPS_WSDL: String = ""
        var QAUTH = "Authorization"
        var TOKEN = ""
        var GET = "GET"
        var POST = "POST"
    }

}
