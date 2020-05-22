package com.seatrend.vendor.allinspection.activity

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.support.v4.content.ContextCompat
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.entity.ShareEntity
import com.seatrend.vendor.allinspection.utils.GsonUtils
import com.seatrend.vendor.allinspection.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_handle.*
import java.util.ArrayList

/**
 * Created by ly on 2020/5/20 15:37
 */
class HandleInspetionActivity : BaseActivity() {

    private val E_INSPECTION = 0x1//环检requestion 标记
    private val PHOTO_INSPECTION = 0x2//照片requestion 标记

    override fun initView() {
        setPageTitle("检验入口")
        bindEvent()
        text.text =  GsonUtils.toJson(SharedPreferencesUtils.getHJCameSpinerList())
    }

    private fun bindEvent() {
        rl_hj.setOnClickListener {
            showToast("开启环检程序")
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.component =
                    ComponentName(
                        "com.seatrend.environment.inspection",
                        "com.seatrend.environment.inspection.RequestAction"
                    )

                val entity = ShareEntity()

                val jb = ShareEntity.JscsBean()
                jb.cphm = getIntent().getStringExtra("hphm")
                jb.cllx = "2"
                jb.hplb = "A"
                jb.syr = "张三"
                jb.syxz = "家用"
                jb.zz = "高新区两江国际一栋10-1006"
                jb.clxh = "ZN5024XZHW1G4"
                jb.clsbdh = "LSW913L2250002627"
                jb.fdjhm = "SS-2250002627"
                jb.clccrq = "2010-10-01"
                jb.hdzk = "5"
                jb.zdzzl = "2134kg"
                jb.zbzl = "3158kg"
                jb.hdzzl = "3158kg"
                jb.gcjk = "国产"
                jb.fdjxh = "SS-22"
                jb.pl = "1.5T"
                jb.edgl = "220kw/h"
                jb.rl = "汽油"
                jb.clscqy = "上海一汽公司"
                jb.clccrq = "2012-10-08"
                jb.dpscs = "德国大众"
                jb.czsfzhm = "身份证"
                jb.czzjmc = "51052119900210071X"
                entity.setJscs(jb)

                val lists = SharedPreferencesUtils.getAllCameSpinerList()
                entity.photo_list = lists
                intent.putExtra("aj_send", GsonUtils.toJson(entity))
                startActivityForResult(intent, E_INSPECTION)
            } catch (e: Exception) {
                showToast("环检程序启动失败，查看是否安装环检程序！")

            }
        }

        rl_zp.setOnClickListener {
            val i = Intent()
            i.setClass(this, PhotoCollectionActivty::class.java)
            startActivityForResult(i, PHOTO_INSPECTION)
        }
        v_next.setOnClickListener {
            showToast("提交完成")
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showLog("  requestCode =  "+requestCode)
        showLog("  resultCode =  "+resultCode)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                E_INSPECTION -> {
                    if (data != null) {
                        tv_hjjl.text = getString(R.string.hj_result, data.getStringExtra("hj_result"))
                        tv_hjjl.setTextColor(ContextCompat.getColor(this, R.color.blue))
                    }
                }
                PHOTO_INSPECTION -> {
                    showLog("sssssss")
                    tv_zp.text = getString(R.string.zp_result)
                    tv_zp.setTextColor(ContextCompat.getColor(this, R.color.blue))
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_handle
    }
}