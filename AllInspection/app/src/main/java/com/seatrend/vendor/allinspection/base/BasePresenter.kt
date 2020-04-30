package com.seatrend.vendor.allinspection.base

import com.seatrend.vendor.allinspection.entity.CommonResponse

/**
 * Created by ly on 2020/4/8 13:18
 */
abstract class BasePresenter(var mView: BaseView) {
    abstract fun doNetworkTask(map: Map<String, String?>, jkid: String,iq:Boolean)
    abstract fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean)
}