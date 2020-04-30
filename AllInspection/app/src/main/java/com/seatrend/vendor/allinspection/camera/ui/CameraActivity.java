package com.seatrend.vendor.allinspection.camera.ui;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import com.seatrend.vendor.allinspection.R;
import com.seatrend.vendor.allinspection.camera.manager.AnimUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ly on 2020/4/26 13:37
 */
public abstract class CameraActivity extends AppCompatActivity {

    @BindView(R.id.surface_view)
    public SurfaceView surface_view;

    protected OrientationEventListener orientationEventListener;

    protected Camera mCamera;
    protected Camera.Parameters mParameters;

    protected int orientation = 0;//方向 度数  0  90 180 270
    protected int mCameraId = 0;  //主副摄id


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestShow();
        initService();
    }

    private void initService() {
        orientationEventListener = new OrientationEventListener(this) {

            @Override
            public void onOrientationChanged(int orientation) {

                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return;  //手机平放时，检测不到有效的角度
                }
//只检测是否有四个角度的改变
                if (orientation > 350 || orientation < 10) { //0度
                    getOrientation(0);
                } else if (orientation > 80 && orientation < 100) { //90度
                    getOrientation(90);
                } else if (orientation > 170 && orientation < 190) { //180度
                    getOrientation(180);
                } else if (orientation > 260 && orientation < 280) { //270度
                    getOrientation(270);
                } else {
                    return;
                }
            }
        };
        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        } else {
            orientationEventListener.disable();//注销
        }
    }


    private void requestShow() {
        requestOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestAllScreen();
    }

    protected void showToast(Object msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }

    protected void showLog(Object msg) {
        Log.d("[lylog]", "----- " + msg);
    }

    /**
     * Configuration.ORIENTATION_LANDSCAPE
     * Configuration.ORIENTATION_PORTRAIT
     *
     * @return
     */
    protected int getUIOrateition() {
        Configuration mConfiguration = this.getResources().getConfiguration();
        return mConfiguration.orientation; //获取屏幕方向
    }


    //设置全屏模式(以屏幕的宽为基准，因为宽<高)
    private void requestAllScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //设置方向

    /**
     * ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
     * ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     *
     * @param orientation
     */
    private void requestOrientation(int orientation) {
        if (getRequestedOrientation() != orientation) {
            setRequestedOrientation(orientation);
        }
    }

    //自动对焦回调
    protected Camera.AutoFocusCallback mAotuFocusCallBack = new Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            //多线程的考虑
            showLog("对焦：" + success);
            showToast("对焦：" + success);
            if (success) {


            }
//
            defParameters(camera); // 默认自动对焦
        }
    };


    //默认相机模式，自动对焦 持续的fc
    protected void defParameters(Camera camera) {
        camera.cancelAutoFocus();
        if (getmCameraId() == Camera.CameraInfo.CAMERA_FACING_FRONT) { //前置
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE); //对焦模式几种？6+（录像的对焦方式 FOCUS_MODE_CONTINUOUS_VIDEO）  普通：FOCUS_MODE_CONTINUOUS_PICTURE
            camera.setParameters(parameters);
        } else if (getmCameraId() == Camera.CameraInfo.CAMERA_FACING_BACK) {//后置
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE); //对焦模式几种？6+（录像的对焦方式 FOCUS_MODE_CONTINUOUS_VIDEO）  普通：FOCUS_MODE_CONTINUOUS_PICTURE
            camera.setParameters(parameters);
        }
        mParameters = camera.getParameters();
    }


    //camera  获取前后摄
    protected int getmCameraId() {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, info);
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            return Camera.CameraInfo.CAMERA_FACING_FRONT;  // 前摄
        }
        return Camera.CameraInfo.CAMERA_FACING_BACK;  // 后摄
    }


    //图片随陀螺仪重心旋转
    protected void comeRotation(View view, int rate) {
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            return;  //横屏 重新计算 下边适合竖屏，懒得计算
        }
        switch (rate) {
            case 270:
                AnimUtil.Rate.startAnimation(view, 500, orientation, rate - 180);
                break;
            case 90:
                if (orientation == 0) {
                    AnimUtil.Rate.startAnimation(view, 500, orientation + 360, rate + 180);
                } else {
                    AnimUtil.Rate.startAnimation(view, 500, orientation, rate + 180);
                }
                break;
            case 180:
                if (orientation == 90) {
                    AnimUtil.Rate.startAnimation(view, 1000, orientation + 180, rate);
                } else {
                    AnimUtil.Rate.startAnimation(view, 1000, orientation - 180, rate);
                }
                break;
            case 0:
                AnimUtil.Rate.startAnimation(view, 1000, orientation - 180, rate);
                break;
        }
        orientation = rate;
    }


    //手动对焦
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                setFocusArea(event);
                break;
        }
        return true;
    }


    private void setFocusArea(MotionEvent event) {
        //关于感光区域的亮度调整
        int areaX = (int) (event.getX() / surface_view.getWidth() * 2000) - 1000; // 获取映射区域的X坐标
        int areaY = (int) (event.getY() / surface_view.getWidth() * 2000) - 1000; // 获取映射区域的Y坐标
        // 创建Rect区域
        Rect focusArea = new Rect();
        focusArea.left = Math.max(areaX - 100, -1000); // 取最大或最小值，避免范围溢出屏幕坐标
        focusArea.top = Math.max(areaY - 100, -1000);
        focusArea.right = Math.min(areaX + 100, 1000);
        focusArea.bottom = Math.min(areaY + 100, 1000);
        // 创建Camera.Area
        Camera.Area cameraArea = new Camera.Area(focusArea, 1000);
        List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
        List<Camera.Area> focusAreas = new ArrayList<Camera.Area>();
        if (mParameters.getMaxNumMeteringAreas() > 0) {
            meteringAreas.add(cameraArea);
            focusAreas.add(cameraArea);
        }
        mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO); // 设置对焦模式(点击屏幕需要自动fc)
        mParameters.setFocusAreas(focusAreas); // 设置对焦区域
        mParameters.setMeteringAreas(meteringAreas); // 设置测光区域
        try {
            mCamera.cancelAutoFocus(); // 每次对焦前，需要先取消对焦之前状态
            mCamera.setParameters(mParameters); // 设置相机参数
            mCamera.autoFocus(mAotuFocusCallBack); // 开启对焦
        } catch (Exception e) {
//                    showToast(e.getMessage() + " id = " + mCameraId); //cameraid = 1  报异常
            e.printStackTrace();
        }
        showLog("点击屏幕 X= " + event.getX() + "  Y= " + event.getY());
    }


    /**
     * ui的旋轉方向
     *
     * @param rate
     */
    abstract void getOrientation(int rate);
}
