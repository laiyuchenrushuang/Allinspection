package com.seatrend.vendor.allinspection

//import com.seatrend.vendor.allinspection.camera.cameraX.CameraXActivity
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.RemoteException
import android.view.View
import com.seatrend.vendor.IInspect
import com.seatrend.vendor.ServiceLisener
import com.seatrend.vendor.allinspection.activity.HandleInspetionActivity
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.base.Constants
import com.seatrend.vendor.allinspection.camera.ui.DefinedCameraActivty
import com.seatrend.vendor.allinspection.entity.HJQuestEntity
import com.seatrend.vendor.allinspection.entity.JKLX
import com.seatrend.vendor.allinspection.entity.SendHJEntity
import com.seatrend.vendor.allinspection.entity.ShareEntity
import com.seatrend.vendor.allinspection.utils.Base64Utils
import com.seatrend.vendor.allinspection.utils.GsonUtils
import com.seatrend.vendor.allinspection.utils.SharedPreferencesUtils
import com.seatrend.vendor.allinspection.zxing.activity.CaptureActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {

    private var iInspect: IInspect? = null
    private val E_INSPECTION = 0x1//环检requestion 标记
    private var aidlManager: AIDLManager? = null


    override fun initView() {
        setPageTitle("机动车检验终端")
        appPermissionReq()
        //"com.mapuni.cdcar"
        bindEvent()

//        showLog("GGGG"+Base64Utils.base64Decode("MTE3MDAxMjAwOTkxMjUwMTU", ""))
        showLog("GGGG = "+Base64Utils.base64Encode(Constants.PDA_USER, ""))
        showLog("GGGG = "+Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER, "")))

        showLog("GGGG = "+Base64Utils.reverse(Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER, ""))))
        showLog("GGGG ="+Base64Utils.base64Decode(Base64Utils.reverse(Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER, ""))), ""))
    }

    override fun onResume() {
        super.onResume()
        if (aidlManager == null) {
            aidlManager = AIDLManager.getIncetance().bindService(this)
        }
    }

    private fun bindEvent() {
        bb.setOnClickListener {
            startActivity(Intent(this, DefinedCameraActivty::class.java))
        }

        //启动小工具
        ll_hj.setOnClickListener {
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

                //不牵涉业务 只传身份证和 接口类型
                val entity = ShareEntity()
                entity.sfxx = Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER,"utf-8"))
                val sjnr = ShareEntity.SJNR()
                sjnr.jklx = JKLX.REQUEST_WIDGET.ordinal
                entity.sjnr = sjnr
                intent.putExtra("aj_send", GsonUtils.toJson(entity))
                showLog(GsonUtils.toJson(entity))
                startActivityForResult(intent, E_INSPECTION)
            } catch (e: Exception) {
                showToast("环检程序启动失败，查看是否安装环检程序！")

            }

        }

        ll_login.setOnClickListener {
            ll_view2.visibility = View.VISIBLE
            ll_view1.visibility = View.GONE
        }

        bt_search.setOnClickListener {

            iInspect = aidlManager!!.build()
            if (iInspect == null) {
                showLog(" iInspect == null")
                iInspect = aidlManager!!.build()
                return@setOnClickListener
            }
            showLoadingDialog()
            thread {
                try {
                    showLog(" sendVehInfo 正常请求")

                    //数据封装
                    /**
                     *
                    {
                    "clsbdh":"01",
                    "hplb":"",
                    "hphm":"川A 12345",
                    "xh":"01",
                    "jyjgbh":"01",
                    "RLZL":"A",
                    "SFXNY":"",
                    "XNYZL":""
                    }
                     */

                    val entity = SendHJEntity()
                    val sjnr = SendHJEntity.SJNR()
                    sjnr.clsbdh = Constants.CLSBDH
                    sjnr.hphm = et_hphm.text.toString()
                    sjnr.xh = "01"
                    sjnr.jyjgbh = "01"
                    sjnr.rlzl = "A"
                    sjnr.hplb = "1"
                    sjnr.jylb = "02"

                    sjnr.xnyzl = "A"
                    sjnr.sfxny = "1"
                    sjnr.yxqz = "1"
                    sjnr.jklx = JKLX.REQUEST_TASK.ordinal
                    entity.sfxx=Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER,"utf-8"))

                    entity.sjnr = sjnr

                    val teststr = "{\"sfxx\":\"zEDNzQjMzADM5kTM3ATMwETN\",\"sjnr\":{\"clsbdh\":\"LFV2A2157A3067571\",\"hphm\":\"川AYY520\",\"hplb\":\"02\",\"jklx\":2,\"jyjgbh\":\"5100000114\",\"jylb\":\"01\",\"rlzl\":\"A\",\"sfxny\":\"2\",\"xh\":\"51010010386319\",\"xnyzl\":\"\",\"yxqz\":\"2020-09-30\"}}"

//                    iInspect!!.sendVehInfo(GsonUtils.toJson(entity), mServiceListener)
                    iInspect!!.sendVehInfo(teststr, mServiceListener)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                    dismissLoadingDialog()
                    showLog(" sendVehInfo 异常 ：" + e.message)
                }
            }
        }

        ivBack!!.setOnClickListener {
            if (ll_view2.visibility == View.VISIBLE) {
                ll_view2.visibility = View.GONE
                ll_view1.visibility = View.VISIBLE

            } else if (ll_view1.visibility == View.VISIBLE) {
                finish()
            }
        }

        test.setOnClickListener {
            //            startActivity(Intent(this,GreenDaoActivity::class.java))
//            startActivity(Intent(this, CameraXActivity::class.java))
        }
        greendao.setOnClickListener {
            //            startActivity(Intent(this, GreenDaoActivity::class.java))
        }
        ll_test.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
        qCode.setOnClickListener {
            startActivity(Intent(this, CaptureActivity::class.java))
        }
    }

    private val mServiceListener = object : ServiceLisener.Stub() {

        override fun currentState(state: Int) {
            dismissLoadingDialog()
            showLog(state)
        }

        override fun serviceError(strError: String) {
            dismissLoadingDialog()
            runOnUiThread {
                showErrorDialog(strError)
                text_search.text = strError
            }

        }

        override fun serviceSuccess(strSuccess: String) {
            dismissLoadingDialog()
            showLog(strSuccess)
//            runOnUiThread {
//                text_search.text = strSuccess
//            }

            runOnUiThread {
                showToast("查询环检信息成功")
            }
            val entity = GsonUtils.gson(strSuccess,HJQuestEntity::class.java)
            showLog("1")
            val intent = Intent()
            intent.setClass(this@MainActivity, HandleInspetionActivity::class.java)
            showLog("2")
            intent.putExtra("hj_send", strSuccess)
            showLog("3")
            intent.putExtra("hphm", et_hphm.text.toString())
            showLog("4")
            SharedPreferencesUtils.setHJCameSpinerList(GsonUtils.toJson(entity.sjnr))
            showLog("5")
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                E_INSPECTION -> {
                    if (data != null) {
                        showToast("环检返回数据：" + data.getStringExtra("hj_result"))
                    }
                }
            }
        }
    }

//    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
//        when (event!!.action) {
//            KeyEvent.KEYCODE_BACK -> {
//                if (iInspect != null) {
//                    iInspect!!.close()
//                }
//            }
//        }
//        return super.dispatchKeyEvent(event)
//    }

    override fun onDestroy() {
        super.onDestroy()
        if (aidlManager!!.build() != null) {
            val entity = SendHJEntity()
            val sjnr = SendHJEntity.SJNR()
            entity.sfxx=Base64Utils.reverse(Base64Utils.base64Encode(Constants.PDA_USER,"utf-8"))
            sjnr.jklx = 99
            entity.sjnr = sjnr
            aidlManager!!.build()!!.sendVehInfo(GsonUtils.toJson(entity), object : ServiceLisener.Stub() {
                override fun currentState(state: Int) {
                }

                override fun serviceError(strError: String?) {
                }

                override fun serviceSuccess(strSuccess: String?) {
                }

            }) //取消监听
        }

        aidlManager!!.unbindService(this)
//        if (iInspect != null) {
//            iInspect = null
//        }
        aidlManager!!.setThreadFlag(false)
        if (aidlManager != null) {
            aidlManager = null
        }
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
