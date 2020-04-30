package com.seatrend.vendor.allinspection

import android.content.Intent
import android.content.pm.ActivityInfo
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.camera.ui.DefinedCameraActivty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun initView() {

        setPageTitle("你好")

        bindEvent()
    }

    private fun bindEvent() {
        bb.setOnClickListener {
            startActivity(Intent(this, DefinedCameraActivty::class.java))
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
