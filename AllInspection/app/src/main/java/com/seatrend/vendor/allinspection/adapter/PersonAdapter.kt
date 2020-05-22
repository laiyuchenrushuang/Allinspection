package com.seatrend.vendor.allinspection.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mydemo.mydblib.entity.PersonInfor
import com.seatrend.vendor.allinspection.R

/**
 * Created by ly on 2020/5/21 15:40
 */
class PersonAdapter(val mContext: Context, var mData: ArrayList<PersonInfor>) :
    RecyclerView.Adapter<PersonAdapter.MyHolder>() {

    fun setData(data: ArrayList<PersonInfor>) {
        this.mData = data
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<PersonInfor> {
        return mData
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.person_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.initView(mData[position])
    }

    inner class MyHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var person_number: TextView? = null
        var person_name: TextView? = null
        var person_sex: TextView? = null
        var person_time: TextView? = null
        var person_order: TextView? = null

        fun initView(personInfor: PersonInfor) {
            person_number = view.findViewById(R.id.person_number)
            person_name = view.findViewById(R.id.person_name)
            person_sex = view.findViewById(R.id.person_sex)
            person_time = view.findViewById(R.id.person_time)
            person_order = view.findViewById(R.id.person_order)

            setData(personInfor)
        }

        private fun setData(personInfor: PersonInfor) {
            person_order!!.text = personInfor.id.toString()
            person_number!!.text = personInfor.perNo
            person_name!!.text = personInfor.name
            person_sex!!.text = personInfor.sex
            person_time!!.text = personInfor.time

        }

    }
}


