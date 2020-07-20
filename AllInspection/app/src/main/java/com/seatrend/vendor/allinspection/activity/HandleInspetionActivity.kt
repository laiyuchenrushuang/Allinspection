package com.seatrend.vendor.allinspection.activity

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.support.v4.content.ContextCompat
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.base.Constants
import com.seatrend.vendor.allinspection.entity.ResultHjEntity
import com.seatrend.vendor.allinspection.entity.ShareEntity
import com.seatrend.vendor.allinspection.utils.GsonUtils
import com.seatrend.vendor.allinspection.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_handle.*

/**
 * Created by ly on 2020/5/20 15:37
 */
class HandleInspetionActivity : BaseActivity() {

    private val E_INSPECTION = 0x1//环检requestion 标记
    private val PHOTO_INSPECTION = 0x2//照片requestion 标记

    override fun initView() {
        setPageTitle("检验入口")
        bindEvent()
        text.text = GsonUtils.toJson(intent.getStringExtra("hj_send"))
//        text.text = GsonUtils.toJson(SharedPreferencesUtils.getHJCameSpinerList())
    }

    private fun bindEvent() {
        rl_hj.setOnClickListener {
            showToast("开启环检程序")
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.component =
                    ComponentName(
                        Constants.APP_PACKAGE.YU_TONG_PACKAGE,
                        Constants.APP_PACKAGE.YU_TONG_UI
//                        Constants.APP_PACKAGE.DEFAULT,
//                        Constants.APP_PACKAGE.DEFAULT_UI
                    )

//                val entity = VehisPara()
//
//                entity.jylsh = "001234567"
//                entity.xh = "001234567"
//                entity.hpzl = "A"
//                entity.hphm = getIntent().getStringExtra("hphm")
//                entity.cllx = "2"
//
//                entity.clpp1 = "德国大众"
//                entity.gcjk = "国产"
//                entity.zzcmc = "德国大众公司"
//                entity.clsbdh = "L0524866663114"
//                entity.fdjxh = "SS-22"
//                entity.fdjh = "SS-22"
//                entity.csys = "白"
//                entity.syxz = "家用"
//                entity.sfzmmc = "身份证"
//                entity.sfzmhm = "51052118885"
//
//                entity.syr = "张三"
//                entity.ccdjrq = "2020-05-06"
//                entity.yxqz = "2020-05-06"
//                entity.qzbfqz = "2020-05-06"
//                entity.fzjg = "川A"
//                entity.xzqh = "510500"
//                entity.zt = "优"
//                entity.rlzl = "汽油"
//
//                entity.pl = "1.5T"
//                entity.gl = "1.5KW"
//                entity.lts = "4"
//                entity.zzl = "5T"
//                entity.zbzl = "3158kg"
//
//                entity.hdzzl = "3158kg"
//                entity.hdzk = "5"
//                entity.zqyzl = "5T"
//                entity.qpzk = "1"
//                entity.qpzk = "4"
//                entity.hbdbqk = "优秀"
//                entity.ccrq = "2020-05-06"
//                entity.zsxzqh = "500521"
//                entity.sfmj = "否"
//                entity.bmjyy = "否"
//                entity.sfxny = "否"
//                entity.xnyzl = "否"
//                entity.yxh = "500521"


                val all = ShareEntity()
//                var str =
//                    "{\"bmjyy\":\"\",\"ccdjrq\":\"2010-09-28 14:13:57\",\"ccrq\":\"2010-08-18\",\"cllx\":\"K33\",\"clpp1\":\"宝来牌\",\"clsbdh\":\"LFV2A2157A3067571\",\"clxh\":\"FV7162XATG\",\"csys\":\"E\",\"fdjh\":\"N34759\",\"fdjxh\":\"CLS\",\"fzjg\":\"川A\",\"gcjk\":\"A\",\"gl\":\"\",\"hbdbqk\":\"\",\"hdzk\":\"5\",\"hdzzl\":\"\",\"hphm\":\"川AYY520\",\"hpzk\":\"\",\"hpzl\":\"02\",\"jylsh\":\"510114200520015200101\",\"lts\":\"\",\"pl\":\"\",\"qpzk\":\"\",\"qzbfqz\":\"2099-12-31\",\"rlzl\":\"A\",\"sfmj\":\"\",\"sfxny\":\"\",\"sfzmhm\":\"5105211......\",\"sfzmmc\":\"A\",\"syr\":\"姚先华\",\"syxz\":\"A\",\"xh\":\"FV7162XATG\",\"xnyzl\":\"\",\"xzqh\":\"\",\"yxqz\":\"2020-09-30\",\"zbzl\":\"1305\",\"zp\":[{\"zplj\":\"/storage/emulated/0/OuterDetectionImgs/1591939855549.jpg\",\"zplx\":\"0111\",\"zpmc\":\"车辆左前方斜视45度照片\"},{\"zplj\":\"/storage/emulated/0/OuterDetectionImgs/1591939855549.jpg\",\"zplx\":\"0111\",\"zpmc\":\"车辆左前方斜视45度照片\"},{\"zplj\":\"/storage/emulated/0/OuterDetectionImgs/1591939414031.jpg\",\"zplx\":\"H001\",\"zpmc\":\"后处理装置1\"}],\"zqyzl\":\"\",\"zsxzqh\":\"\",\"zt\":\"A\",\"zzcmc\":\"一汽-大众汽车有限公司\",\"zzl\":\"1845\"}"
                var str =
                    "{\"sfxx\":\"zEDNzQjMzADM5kTM3ATMwETN\",\"sjnr\":{\"bmjyy\":\"该机动车检验有效期止距初次登记日期超过6年，不能办理免检业务！\",\"ccdjrq\":\"2010-09-28 14:13:57\",\"ccrq\":\"2010-08-18\",\"cllx\":\"K33\",\"clpp1\":\"宝来牌\",\"clsbdh\":\"LFV2A2157A3067571\",\"clxh\":\"FV7162XATG\",\"csys\":\"E\",\"fdjh\":\"N34759\",\"fdjxh\":\"CLS\",\"fzjg\":\"川A\",\"gcjk\":\"A\",\"gl\":\"77\",\"hbdbqk\":\"GB18352.3-2005国Ⅳ\",\"hdzk\":\"5\",\"hdzzl\":\"0\",\"hjcs\":\"1\",\"hphm\":\"川AYY520\",\"hpzk\":\"0\",\"hpzl\":\"02\",\"jklx\":3,\"jylsh\":\"510114200716013940101\",\"lts\":\"4\",\"pl\":\"1598\",\"qpzk\":\"0\",\"qzbfqz\":\"2099-12-31\",\"rlzl\":\"A\",\"sfmj\":\"2\",\"sfxny\":\"2\",\"sfzmhm\":\"510108********1813\",\"sfzmmc\":\"A\",\"syr\":\"姚先华\",\"syxz\":\"A\",\"xh\":\"51010010386319\",\"xnyzl\":\"\",\"xzqh\":\"510108\",\"yxqz\":\"2020-09-30\",\"zbzl\":\"1305\",\"zqyzl\":\"0\",\"zsxzqh\":\"510108\",\"zt\":\"A\",\"zzcmc\":\"一汽-大众汽车有限公司\",\"zzl\":\"1845\"}}"

                var entity = GsonUtils.gson(str, ShareEntity::class.java);

//                entity.jklx = JKLX.LAUNCH_TASK.ordinal
//                entity.hjcs = "1"
//                entity.clsbdh = Constants.CLSBDH

                try {
                    val lists = SharedPreferencesUtils.getAllCameSpinerList()
                    entity.sjnr.zp = lists!!
                } catch (e: Exception) {
                }
//                all.sfxx = Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER, "utf-8"))
                showLog("aj_send = " + GsonUtils.toJson(entity))
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
        showLog("  requestCode =  " + requestCode)
        showLog("  resultCode =  " + resultCode)
        if (data != null) {
            showLog("  resultCode =  " + data.getStringExtra("hj_result"))
        }

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                E_INSPECTION -> {
                    if (data != null) {

                        var result = GsonUtils.gson(data.getStringExtra("hj_result"), ResultHjEntity::class.java)
                        if (result != null) {
                            tv_hjjl.text = getString(R.string.hj_result, if (result.sjnr.jyjl == "1") "合格" else "不合格")
                            tv_hjjl.setTextColor(ContextCompat.getColor(this, R.color.blue))
                        }
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