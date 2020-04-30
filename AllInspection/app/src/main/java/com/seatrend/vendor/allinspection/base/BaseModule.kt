package com.seatrend.vendor.allinspection.base

import com.seatrend.vendor.allinspection.entity.CommonResponse

/**
 * Created by ly on 2020/4/8 13:20
 */
abstract class BaseModule {

    abstract fun doWork(map: Map<String, String?>, jkid: String,iq:Boolean)
    abstract fun doWorkResults(commonResponse: CommonResponse, isSuccess: Boolean)

}