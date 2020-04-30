package com.seatrend.vendor.allinspection.http

import com.seatrend.vendor.allinspection.base.BasePresenter
import com.seatrend.vendor.allinspection.entity.CommonResponse

/**
 * Created by ly on 2020/4/8 13:17
 */
class NormalPresenter(mView:NormalView) : BasePresenter(mView){
    private var mNormalModule: NormalModule?=null
    private var mNormalView: NormalView?=null
    init {
        mNormalModule= NormalModule(this)
        mNormalView=mView;
    }
    override fun doNetworkTask(map: Map<String, String?>, jkid: String,isQuery:Boolean) {
        mNormalModule!!.doWork(map,jkid,isQuery)
    }

    override fun requestResults(commonResponse: CommonResponse, isSuccess: Boolean) {
        if (isSuccess){
            mNormalView!!.netWorkTaskSuccess(commonResponse)
        }else {
            mNormalView!!.netWorkTaskfailed(commonResponse)
        }

    }
}