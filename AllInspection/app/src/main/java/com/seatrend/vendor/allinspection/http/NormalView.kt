package com.seatrend.vendor.allinspection.http

import com.seatrend.vendor.allinspection.base.BaseView
import com.seatrend.vendor.allinspection.entity.CommonResponse

/**
 * Created by ly on 2020/4/8 13:17
 */
interface NormalView : BaseView {
    fun netWorkTaskSuccess(commonResponse: CommonResponse)
    fun netWorkTaskfailed(commonResponse: CommonResponse)
}