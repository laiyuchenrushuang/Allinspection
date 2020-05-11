package com.seatrend.vendor.allinspection.activity

import com.bumptech.glide.Glide
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_picture.*

/**
 * Created by ly on 2020/5/8 18:14
 */
class ShowPictureActivity:BaseActivity() {
    override fun initView() {
        setPageTitle("图片详情")
        img.enable()//启动缩放
        Glide.with(this).load(intent.getStringExtra("photo_path")).into(img)
    }

    override fun getLayout(): Int {
        return R.layout.activity_picture
    }
}