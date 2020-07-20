package com.seatrend.vendor.allinspection

import android.view.View
import com.seatrend.vendor.allinspection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*

/**
 * Created by ly on 2020/6/19 17:40
 */
class TestActivity  :BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_1->{
                showToast("你点到我了！")
            }
            R.id.iv_2->{
                showToast("你点我干什么？")
            }
            R.id.iv_3->{
                showToast("你点我好玩吗？")
            }
            R.id.iv_4->{
                showToast("你点什么啊！")
            }
            R.id.iv_5->{
                showToast("你点到了什么？")
            }
            R.id.iv_6->{
                showToast("你点啥点！")
            }
            R.id.iv_7->{
                showToast("你点到这里了.")
            }
            R.id.iv_8->{
                showToast("你点啊点 点啊点...")
            }
            R.id.iv_9->{
                showToast("你点，我叫你再点！")
            }

        }
    }

    override fun initView() {

        iv_1.setOnClickListener(this)
        iv_2.setOnClickListener(this)
        iv_3.setOnClickListener(this)
        iv_4.setOnClickListener(this)
        iv_5.setOnClickListener(this)
        iv_6.setOnClickListener(this)
        iv_7.setOnClickListener(this)
        iv_8.setOnClickListener(this)
        iv_9.setOnClickListener(this)

    }

    override fun getLayout(): Int {
        return R.layout.activity_test
    }

}