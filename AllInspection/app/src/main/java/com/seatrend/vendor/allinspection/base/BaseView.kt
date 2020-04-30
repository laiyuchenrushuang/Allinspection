package com.seatrend.vendor.allinspection.base

/**
 * Created by ly on 2020/4/8 10:13
 */
interface BaseView {
    fun showToast(msg: String)
    fun showLog(msg: String)
    fun showErrorDialog(msg: String)
}