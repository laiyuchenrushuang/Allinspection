package com.seatrend.vendor.allinspection

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.view.View
import com.seatrend.vendor.IInspect
import com.seatrend.vendor.ServiceLisener
import com.seatrend.vendor.allinspection.activity.GreenDaoActivity
import com.seatrend.vendor.allinspection.activity.HandleInspetionActivity
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.camera.ui.DefinedCameraActivty
import com.seatrend.vendor.allinspection.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {

    private val E_INSPECTION = 0x1//环检requestion 标记

    private var iInspect: IInspect? = null

    override fun initView() {
        setPageTitle("机动车检验终端")
        appPermissionReq()

        bindEvent()
    }

    override fun onResume() {
        super.onResume()
        bindServiceByAidl() //aidl
        registerListener(mServiceListener)
    }

    private fun registerListener(listener: ServiceLisener.Stub) {

        if (!checkBinderIsAlive()) {
            showLog("Binder 已死")
            return
        }

        thread {
            while (true) {
                if (iInspect == null) {
                    showLog("iInspect == null")
                    continue
                }
                iInspect!!.registerListener(listener)
                break
            }
        }
    }

    //binder 是否存活状态
    private fun checkBinderIsAlive(): Boolean {
        if (iInspect != null && iInspect!!.asBinder().isBinderAlive) {
            return true
        }
        return false
    }

    //只调用一次哦  不然 一直重新绑定
    @Synchronized
    private fun bindServiceByAidl() {
        val intent = Intent("com.seatrend.vendor.respond_message")
        intent.setPackage("com.seatrend.environment.inspection")
        bindService(intent, cnnec, Context.BIND_AUTO_CREATE)
    }

    private val cnnec = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            showLog("onServiceConnected")
            iInspect = IInspect.Stub.asInterface(service)

            try {
                // 注册死亡代理
                if (iInspect != null) {
                    service.linkToDeath(mDeathRecipient, 0)
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

        }

        override fun onServiceDisconnected(name: ComponentName) {
            showLog("onServiceDisconnected")
        }
    }


    /**
     * 监听Binder是否死亡
     */
    private val mDeathRecipient = object : IBinder.DeathRecipient {
        override fun binderDied() {
            if (iInspect == null) {
                return
            }
            iInspect!!.asBinder().unlinkToDeath(this, 0)
            iInspect = null
            //重新绑定
            bindServiceByAidl()
        }
    }


    private fun bindEvent() {
        bb.setOnClickListener {
            startActivity(Intent(this, DefinedCameraActivty::class.java))
        }

        ll_hj.setOnClickListener {
            showToast("开启环检程序")
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.component =
                    ComponentName(
                        "com.seatrend.environment.inspection",
                        "com.seatrend.environment.inspection.RequestAction"
                    )
                intent.putExtra(
                    "aj_send", "数据状态正常 :\n"
                            + "车牌号码 ：川A 45853\n"
                            + "所有人姓名：张三\n"
                            + "所有人身份证：5105211800366...\n"
                            + "车身颜色：白\n"
                            + "整车编码：L5224452221...\n"
                            + "其他信息.....\n"
                )
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

            if (iInspect == null) {
                showLog(" iInspect == null")
                return@setOnClickListener
            }
            showLoadingDialog()
            thread {
                try {
                    showLog(" sendVehInfo 正常请求")
                    iInspect!!.sendVehInfo(et_hphm.text.toString(), mServiceListener)
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
            } else {
                finish()
            }
        }

        test.setOnClickListener {
            startActivity(Intent(this,GreenDaoActivity::class.java))
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
            showLog("1")
            val intent = Intent()
            intent.setClass(this@MainActivity, HandleInspetionActivity::class.java)
            showLog("2")
            intent.putExtra("hj_send", strSuccess)
            showLog("3")
            intent.putExtra("hphm", et_hphm.text.toString())
            showLog("4")
            SharedPreferencesUtils.setHJCameSpinerList(strSuccess)
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

    override fun onDestroy() {
        super.onDestroy()
        unbindService(cnnec)
        if (iInspect != null) {
//            iInspect!!.unRegisterListener(mServiceListener) //取消监听
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
