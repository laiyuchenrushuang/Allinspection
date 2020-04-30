package com.seatrend.vendor.allinspection.camera.ui;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.seatrend.vendor.allinspection.R;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Created by ly on 2020/4/26 13:42
 */
public class DefinedCameraActivty extends CameraActivity {

    @BindView(R.id.btn_capture)
    public ImageView btn_capture;
    @BindView(R.id.change_camera)
    public ImageView change_camera;

    private SurfaceHolder mSurfaceHolder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_camera_s);
        ButterKnife.bind(this);
        initView();
        bindEvent();
    }

    private void initCamera() {
        if (mCamera == null) {
            mCamera = getCamera();
            if (mSurfaceHolder != null) {
                setStartPreview(mCamera, mSurfaceHolder);
            }
        }
    }

    private void initView() {
        initSurface();
    }

    private void initSurface() {
        mSurfaceHolder = surface_view.getHolder();
        mSurfaceHolder.addCallback(mSurfaceCallBack);
    }

    private void bindEvent() {
        btn_capture.setOnClickListener(v -> {

        });

        change_camera.setOnClickListener(v -> {
            if (getmCameraId() == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mCameraId = 1;
                releaseCamera();
                initCamera();
            } else {
                mCameraId = 0;
                releaseCamera();
                initCamera();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    @Override
    void getOrientation(int rate) {
        if (orientation != rate) {
            comeRotation(btn_capture, rate);
        }
    }

    private SurfaceHolder.Callback mSurfaceCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            setStartPreview(mCamera, mSurfaceHolder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            releaseCamera();
        }
    };

    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            defParameters(camera);
            camera.setPreviewDisplay(holder);
//            camera.setDisplayOrientation(90);//竖屏才旋转  横屏 不用旋转
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open(mCameraId);
        } catch (Exception e) {
            camera = null;
        }
        return camera;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }
}
