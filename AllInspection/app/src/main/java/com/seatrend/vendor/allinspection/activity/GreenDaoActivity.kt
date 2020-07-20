package com.seatrend.vendor.allinspection.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mydemo.mydblib.controller.PerSonController
import com.mydemo.mydblib.entity.PersonInfor
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.adapter.PersonAdapter
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.utils.StringUtils
import com.seatrend.vendor.allinspection.utils.cache.ACache
import kotlinx.android.synthetic.main.activity_greendao.*

/**
 * Created by ly on 2020/5/21 15:00
 */
class GreenDaoActivity : BaseActivity(), View.OnClickListener {


    var id = 0L
    var personAdapter: PersonAdapter? = null
    var cache :ACache ?=null
    override fun initView() {
        setPageTitle("GreenDao运用")
        cache = ACache.get(this)
        initData()
        initRecycleView()
        bindEvent()
    }

    private fun initData() {
        person_number.setText(cache!!.getAsString("no"))
        person_name.setText(cache!!.getAsString("name"))
        person_sex.setText(cache!!.getAsString("sex"))
    }

    private fun bindEvent() {
        add.setOnClickListener(this)
        delete.setOnClickListener(this)
        update.setOnClickListener(this)
        query.setOnClickListener(this)
        query_all.setOnClickListener(this)
        delete_all.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        setCache()
        when (v!!.id) {
            R.id.add -> {
                val entity = getEntity()
//                PerSonController.getInstance(this).insert(entity)
                PerSonController.getInstance(this).insertOrReplace(entity)
                //更新UI
                updateUI()
            }
            R.id.delete -> {
                val entity = getEntity()
                PerSonController.getInstance(this).delete(entity)
                //更新UI
                updateUI()
            }
            R.id.update -> {
                val entity = getEntity()
                PerSonController.getInstance(this).update(entity)
                //更新UI
                updateUI()
            }
            R.id.query -> {
                val entity = getEntity()

                //显示查询的列表
                val list = PerSonController.getInstance(this).query(entity)
                personAdapter!!.setData(list as ArrayList<PersonInfor>)
            }

            R.id.query_all -> {
                //更新UI
                updateUI()
            }
            R.id.delete_all -> {
                PerSonController.getInstance(this).deleteAll()
                //更新UI
                updateUI()
            }
        }

    }

    private fun setCache() {
        cache!!.put("no",person_number.text.toString())
        cache!!.put("name",person_name.text.toString())
        cache!!.put("sex",person_sex.text.toString())
    }

    private fun getEntity(): PersonInfor? {
        val entity = PersonInfor()
        entity.perNo = StringUtils.isNull(person_number.text.toString())
        entity.name = StringUtils.isNull(person_name.text.toString())
        entity.sex = StringUtils.isNull(person_sex.text.toString())
        entity.time = StringUtils.longToStringNoDate(System.currentTimeMillis())
//        entity.id =  id++
        return entity
    }

    //当前数据库的内容
    private fun updateUI() {
        runOnUiThread {
            val list = PerSonController.getInstance(this).searchAll()
            personAdapter!!.setData(list as ArrayList<PersonInfor>)
        }
    }

    private fun initRecycleView() {
        person_recyclerview.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(this, ArrayList<PersonInfor>())
        person_recyclerview.adapter = personAdapter
    }

    override fun getLayout(): Int {
        return R.layout.activity_greendao
    }
}