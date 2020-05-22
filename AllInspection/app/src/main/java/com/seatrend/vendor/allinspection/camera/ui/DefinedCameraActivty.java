package com.seatrend.vendor.allinspection.camera.ui;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.seatrend.vendor.allinspection.R;
import com.seatrend.vendor.allinspection.activity.ShowPictureActivity;
import com.seatrend.vendor.allinspection.base.Constants;
import com.seatrend.vendor.allinspection.utils.BitmapUtils;
import com.seatrend.vendor.allinspection.utils.CalenderUtils;
import com.seatrend.vendor.allinspection.utils.CheckUtil;
import com.seatrend.xj.electricbicyclesalesystem.http.thread.ThreadPoolManager;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ly on 2020/4/26 13:42
 */
public class DefinedCameraActivty extends CameraActivity {

    @BindView(R.id.btn_capture)
    public ImageView btn_capture;
    @BindView(R.id.change_camera)
    public ImageView change_camera;
    @BindView(R.id.time)
    public TextView time;


    private SurfaceHolder mSurfaceHolder;

    MediaRecorder mMediaRecorder;
    private boolean isRecording;//正在录制

    int i = 0;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(() -> {
                time.setText(CalenderUtils.countTime(i++));
            });
        }
    };
    //点击回调
    private Camera.ShutterCallback shutterCallback = () -> {

    };
    //图片回调
    private Camera.PictureCallback jpegCallback = (data, camera) -> {
        rePreviewCamera();
        ThreadPoolManager.Companion.getInstance().execute(new Thread() {
            @Override
            public void run() {
                savePhoto(data);
            }
        });
    };

    private synchronized void savePhoto(byte[] data) {

        File photoFile = BitmapUtils.saveCameraPhoto(data);

        long t1 = System.currentTimeMillis();
        showLog(t1);
        //方案一 自定义
//            Bitmap bt = BitmapUtils.getSmallBitmap(photoFile.getPath());
//            Bitmap bitmap = BitmapUtils.compressImage(bt,200,100);
//            BitmapUtils.saveBitmap(bitmap, photoFile.getName());
//            bitmap.recycle();

        //方案1.1  压缩和文件一并生成(1.5s左右的处理时间)
//        showLog(BitmapUtils.compressImage(BitmapUtils.getSmallBitmap(photoFile.getPath()), 200, 100, photoFile));

        //方案二 Luban
//            Luban.with(this).load(photoFile).ignoreBy(100).setTargetDir(photoFile.getParentFile().getPath()).setRenameListener(filePath -> photoFile.getName().replace(".jpg","luban.jpg")).launch();

        long t2 = System.currentTimeMillis();
        showLog(t2 + "  dis = " + (t2 - t1));
        showLog("photo_path  = " + photoFile.getPath());

        Intent intent = getIntent();
        intent.putExtra("photo_path", photoFile.getPath());
        intent.setClass(this, ShowPictureActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_camera_s);
        ButterKnife.bind(this);
        initView();
        bindEvent();
        initTimeData();
    }



    private void initTimeData() {
        Timer t = new Timer();
        t.schedule(timerTask, 1000, 1000);
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

    /**
     * 开始录制
     */
    private void startRecord() {
        if (mMediaRecorder != null) {
            //没有外置存储, 直接停止录制
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return;
            }

            //mMediaRecorder.reset();
            mCamera.unlock();
            mMediaRecorder.setCamera(mCamera);
            //从相机采集视频
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            // 从麦克采集音频信息
            //mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            //设置视频格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setVideoSize(1280, 720);
            //每秒的帧数
            mMediaRecorder.setVideoFrameRate(24);
            //编码格式
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置帧频率，然后就清晰了
            mMediaRecorder.setVideoEncodingBitRate(1 * 1024 * 1024 * 100);
            // TODO: 2016/10/20 临时写个文件地址, 稍候该!!!

            File videoFile = new File(Constants.Companion.getVIDEO_PATH());
            if (!videoFile.exists()) {
                videoFile.mkdirs();
            }
            mMediaRecorder.setOutputFile(Constants.Companion.getVIDEO_PATH() + SystemClock.currentThreadTimeMillis() + ".mp4");
            mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
            //解决录制视频, 播放器横向问题
            mMediaRecorder.setOrientationHint(90);
            try {
                mMediaRecorder.prepare();
                //正式录制
                mMediaRecorder.start();
                isRecording = true;
            } catch (Exception e) {
                e.printStackTrace();
                showLog(e.getMessage());
            }

        }
    }

    private void initSurface() {
        mSurfaceHolder = surface_view.getHolder();
        mSurfaceHolder.addCallback(mSurfaceCallBack);
    }

    private void bindEvent() {
        btn_capture.setOnClickListener(v -> {
            tabkePic();
            playSystemSound();
//            BitmapUtils.compressImage(BitmapUtils.getSmallBitmap("/storage/emulated/0/AllInspect/CAMERA_PICTRUE/IMG_20200515_113835_1.jpg"), 200, 100, new File("/storage/emulated/0/AllInspect/CAMERA_PICTRUE/IMG_20200515_113835_1.jpg"));

//            startRecord();
        });

        btn_capture.setOnLongClickListener(v -> {
            startRecord();
//            initTimeData();
            i = 0;
            time.setVisibility(View.VISIBLE);
            return false;
        });

        change_camera.setOnLongClickListener(v -> {
            if (isRecording) {
                mMediaRecorder.stop();
            }
            timerTask.cancel();
            time.setVisibility(View.GONE);
            return false;
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

    /**
     * 拍照
     *
     * @time 2014.6.19_1
     */
    public void tabkePic() {
        if (!CheckUtil.isFastClick()) {
            try {
                mCamera.takePicture(shutterCallback, null, jpegCallback);// 调用Camera的拍照方法
            } catch (Exception e) {
                rePreviewCamera();
            }
        }
    }


    /**
     * 在camera执行过程中，异常，则进行二次重新预览相机
     */
    private void rePreviewCamera() {
        if (mCamera != null) {
            try {
                mCamera.stopPreview();
            } catch (Exception ee) {
            }
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();
            } catch (Exception e) {
            }
        }
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
            if (mMediaRecorder == null) {
                mMediaRecorder = new MediaRecorder();
            }
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
        mCurrentStartFocusTime = 0;
    }
}
