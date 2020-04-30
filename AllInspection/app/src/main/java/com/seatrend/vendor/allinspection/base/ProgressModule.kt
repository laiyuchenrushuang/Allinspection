package com.seatrend.vendor.allinspection.base

import com.seatrend.vendor.allinspection.entity.CommonProgress

/**
 * Created by ly on 2020/4/8 14:00
 */
abstract class ProgressModule : BaseModule() {
    abstract fun downloadProgress(commonProgress: CommonProgress)
}