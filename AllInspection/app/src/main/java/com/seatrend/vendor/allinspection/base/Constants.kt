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

        val SHARE_PRE_KEY = "share_pre_key"
        val E_INSPECTION_PHOTO = "e_inspection_photo"
        val ALL_PHOTO = "all_photo"

        val PDA_USER = "41128219921112311X"
        val CLSBDH = "LFV2A2157A3067571"
    }

    object APP_PACKAGE {
            val DEFAULT = "com.seatrend.environment.inspection"
            val DEFAULT_UI = DEFAULT+".RequestAction"

            val YU_TONG_PACKAGE = "com.mapuni.cdcar"
            val YU_TONG_UI = DEFAULT + ".RequestAction"
    }
}
