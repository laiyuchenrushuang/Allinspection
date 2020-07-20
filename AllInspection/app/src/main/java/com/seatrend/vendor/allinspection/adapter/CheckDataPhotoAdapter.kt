package com.seatrend.vendor.allinspection.adapter

/**
 * Created by ly on 2020/5/20 16:06
 */

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.activity.ShowPictureActivity
import com.seatrend.vendor.allinspection.entity.VehisPara
import java.util.*

/**
 * Created by seatrend on 2019/5/15.
 */
class CheckDataPhotoAdapter(private var mContext: Context? = null) :
    RecyclerView.Adapter<CheckDataPhotoAdapter.MyViewHolder>() {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initItemView(data[position])
    }

    private var data = ArrayList<VehisPara.ZpBean>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_check_data_photo_adapter, parent, false)
        return MyViewHolder(view)
    }

    fun setPhotoType(list: ArrayList<VehisPara.ZpBean>) {
        this.data = list
//        Collections.sort(data, mCompareR)
        notifyDataSetChanged()
    }

    fun setPhoto(position: Int, path: String) {
        data[position].zplj = path
        notifyItemChanged(position)
    }

    fun getDataList(): ArrayList<VehisPara.ZpBean> {
        return this.data
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvType: TextView? = null
        private var ivPhoto: ImageView? = null
        private var ivDelete: ImageView? = null

        init {
            tvType = itemView.findViewById(R.id.tv_type)
            ivPhoto = itemView.findViewById(R.id.iv_photo)
            ivDelete = itemView.findViewById(R.id.iv_delete)
            Log.d("lylog","  adapterPosition = "+adapterPosition)


            ivPhoto!!.setOnClickListener {
                if (mOnClickListener != null && data[adapterPosition].zplj == null) {
                    mOnClickListener!!.itemOnClick(adapterPosition)
                }

                if(data[adapterPosition].zplj != null && data[adapterPosition].zplj !=""){
                    val intent = Intent()
                    intent.setClass(mContext, ShowPictureActivity::class.java)
                    intent.putExtra("zpmc", data.get(adapterPosition).zpmc)
                    intent.putExtra("photo_path", data.get(adapterPosition).zplj)
                    mContext!!.startActivity(intent)
                }
            }



            ivDelete!!.setOnClickListener {
                data[adapterPosition].zplj = null
                notifyItemChanged(adapterPosition)
                mDeleteListener!!.itemdelete(adapterPosition)
            }

        }

        fun initItemView(bean: VehisPara.ZpBean) {
            tvType!!.text = bean.zpmc
            tvType!!.setTextColor(Color.BLACK)
            if (bean.zplj != null && bean.zplj.isNotEmpty()) {
                Glide.with(mContext).load(bean.zplj).centerCrop().error(R.mipmap.error_image).into(ivPhoto)
                ivDelete!!.visibility = View.VISIBLE
            } else {
                ivPhoto!!.setImageResource(R.mipmap.take_photo)
                ivPhoto!!.scaleType = ImageView.ScaleType.CENTER
                ivDelete!!.visibility = View.GONE
            }
            if(bean.isSfbp){
                tvType!!.setTextColor(ContextCompat.getColor(mContext!!,R.color.red))
            }else{
                tvType!!.setTextColor(ContextCompat.getColor(mContext!!,R.color.black))
            }
        }
    }

    private var mOnClickListener: itemOnClickListener? = null

    fun setItemOnClick(listener: itemOnClickListener) {
        mOnClickListener = listener
    }

    interface itemOnClickListener {
        fun itemOnClick(position: Int)
    }

    private var mDeleteListener: itemDeleteClickListener? = null

    fun setItemdeleteClick(listen: itemDeleteClickListener) {
        mDeleteListener = listen
    }

    interface itemDeleteClickListener {
        fun itemdelete(position: Int)
    }
}