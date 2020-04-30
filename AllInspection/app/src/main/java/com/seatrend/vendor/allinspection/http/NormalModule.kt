package com.seatrend.vendor.allinspection.http

import com.seatrend.vendor.allinspection.base.BaseModule
import com.seatrend.vendor.allinspection.MyApplication
import com.seatrend.vendor.allinspection.entity.CommonResponse

/**
 * Created by ly on 2020/4/8 13:20
 */
class NormalModule : BaseModule {

    private var mNormalPresenter: NormalPresenter? = null

    constructor(mNormalPresenter: NormalPresenter?) : super() {
        this.mNormalPresenter = mNormalPresenter
    }

    override fun doWork(map: Map<String, String?>, jkid: String,isQuery:Boolean) {
        HttpService.getInstance().request(MyApplication.myApplicationContext,map,jkid,isQuery,this)
    }

    override fun doWorkResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        mNormalPresenter!!.requestResults(commonResponse!!, isSuccess)
    }
}