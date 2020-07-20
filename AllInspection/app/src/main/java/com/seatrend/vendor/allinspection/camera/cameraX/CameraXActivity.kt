package com.seatrend.vendor.allinspection.camera.cameraX

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import com.mydemo.camerax.config.size.Size
import com.mydemo.camerax.enums.CameraFace
import com.mydemo.camerax.enums.CameraSizeFor
import com.mydemo.camerax.listener.CameraOpenListener
import com.mydemo.camerax.listener.CameraPhotoListener
import com.seatrend.vendor.allinspection.R
import com.seatrend.vendor.allinspection.activity.ShowPictureActivity
import com.seatrend.vendor.allinspection.base.BaseActivity
import com.seatrend.vendor.allinspection.utils.BitmapUtils
import com.seatrend.vendor.allinspection.utils.GsonUtils
import kotlinx.android.synthetic.main.activty_camerax.*


/**
 * Created by ly on 2020/5/22 15:47
 */
class CameraXActivity : BaseActivity() {
    override fun initView() {

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE//横屏

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        openCamera()
    }

    private fun openCamera() {

        val wm = this
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outPoint = Point()
        wm.defaultDisplay.getRealSize(outPoint)

//        val wm = this
//            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)

        showLog("  outPoint width = "+outPoint.x  + "  height = "+outPoint.y)
        showLog("  outMetrics width = "+outMetrics.widthPixels  + "  height = "+outMetrics.heightPixels)
        val s = Size.of(4160,3120)
        cv.setExpectSize(s)
        cv.isAutoFocus


        cv.openCamera(object : CameraOpenListener {
            override fun onCameraOpened(cameraFace: Int) {
                showLog("onCameraOpened  cameraid = " + cameraFace)
            }

            override fun onCameraOpenError(throwable: Throwable?) {
                showLog("error : $throwable")
            }
        })

        btn_capture.setOnClickListener {
            cv.takePicture(object : CameraPhotoListener {
                override fun onPictureTaken(data: ByteArray?) {
                    val photoFile = BitmapUtils.saveCameraPhoto(data)
                    val bt: Bitmap = BitmapUtils.getSmallBitmap(photoFile.path)
                    val bitmap = BitmapUtils.compressImage(bt)
                    BitmapUtils.saveBitmap(bitmap,photoFile.name)

                        showLog("photoFile : ${photoFile.path}")
                    cv.resumePreview()
                    intent.setClass(this@CameraXActivity, ShowPictureActivity::class.java)
                    intent.putExtra("zpmc", "拍照详情")
                    intent.putExtra("photo_path", photoFile.path)
                    startActivity(intent)

                }

                override fun onCaptureFailed(throwable: Throwable?) {
                }

            })
            val a1 = cv.getSize(CameraSizeFor.SIZE_FOR_PICTURE)
            val a2 = cv.getSize(CameraSizeFor.SIZE_FOR_PREVIEW)
            val a3 = cv.getSize(CameraSizeFor.SIZE_FOR_VIDEO)

            showLog(GsonUtils.toJson(a1))
            showLog(GsonUtils.toJson(a2))
            showLog(GsonUtils.toJson(a3))


        }

        change_camera.setOnClickListener {
            if (cv.cameraFace == CameraFace.FACE_FRONT) {
                cv.switchCamera(CameraFace.FACE_REAR)
            } else {
                cv.switchCamera(CameraFace.FACE_FRONT)
            }

        }

    }


    override fun getLayout(): Int {
        return R.layout.activty_camerax
    }
}