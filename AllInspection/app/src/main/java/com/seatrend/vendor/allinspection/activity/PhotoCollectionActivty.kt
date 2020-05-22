package com.seatrend.vendor.allinspection.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.widget.StaggeredGridLayoutManager
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.adapter.CheckDataPhotoAdapter
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.base.Constants
import com.seatrend.vendor.allinspection.entity.CameSpinner
import com.seatrend.vendor.allinspection.entity.ShareEntity
import com.seatrend.vendor.allinspection.utils.GsonUtils
import com.seatrend.vendor.allinspection.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.recyclerview.*
import java.io.File

class PhotoCollectionActivty : BaseActivity(), CheckDataPhotoAdapter.itemOnClickListener,
    CheckDataPhotoAdapter.itemDeleteClickListener {
    var allPhoto = ArrayList<ShareEntity.PhotoListBean>()
    private var photoPosition = 0
    private var imgFile: File? = null
    private val CAMERA_REQUEST_CODE = 20


    override fun itemdelete(position: Int) {

    }

    override fun itemOnClick(position: Int) {

        photoPosition = position
        if (allPhoto[position].zplj == null || allPhoto[position].zplj == "") {

            getPicFromCamera()
        }
    }

    private var mCheckDataPhotoAdapter: CheckDataPhotoAdapter? = null

    override fun initView() {
        setPageTitle("采集照片")

        val list_hj = SharedPreferencesUtils.getHJCameSpinerList()

        getDefaultPhoto()
        for (db in list_hj) {
            val entity = ShareEntity.PhotoListBean()
            entity.zplx = db.dmz
            entity.zpmc = db.dmsm1
            allPhoto.add(entity)
        }
        m_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mCheckDataPhotoAdapter = CheckDataPhotoAdapter(this)
        mCheckDataPhotoAdapter!!.setPhotoType(allPhoto)
        mCheckDataPhotoAdapter!!.setItemOnClick(this)
        mCheckDataPhotoAdapter!!.setItemdeleteClick(this)
        m_recycler_view.adapter = mCheckDataPhotoAdapter

        bindEvent()
    }

    private fun bindEvent() {
        ivBack!!.setOnClickListener {
            val str = GsonUtils.toJson(allPhoto)
            SharedPreferencesUtils.setAllCameSpinerList(str)
            setResult(Activity.RESULT_OK)
            finish()
        }
        v_next!!.setOnClickListener {
            val str = GsonUtils.toJson(allPhoto)
            SharedPreferencesUtils.setAllCameSpinerList(str)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun getDefaultPhoto() {

        val entity = ShareEntity.PhotoListBean()
        entity.zpmc = "车辆识别代号照片"
        entity.zplx = "0113"
        allPhoto.add(entity)

        val entity1 = ShareEntity.PhotoListBean()
        entity1.zpmc = "驾驶人座椅汽车安全带"
        entity1.zplx = "0157"
        allPhoto.add(entity1)

        val entity2 = ShareEntity.PhotoListBean()
        entity2.zpmc = "车辆左前方斜视45°照片"
        entity2.zplx = "0111"
        allPhoto.add(entity2)
    }

    private fun getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        val tempFile = File(Constants.Companion.PICTRUE_PATH)//
        val imageUri: Uri
        if (!tempFile.exists()) {
            tempFile.mkdirs()
        }
        imgFile = File(tempFile, System.currentTimeMillis().toString() + ".jpg")
        if (Build.VERSION.SDK_INT >= 24) {//判断版本
            imageUri = FileProvider.getUriForFile(this, getString(R.string.authority), imgFile!!)
        } else {
            imageUri = Uri.fromFile(imgFile)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    @SuppressLint("StringFormatMatches")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            allPhoto[photoPosition].zplj = imgFile!!.path
            mCheckDataPhotoAdapter!!.setPhoto(photoPosition, imgFile!!.path)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_photo
    }
}
