package com.seatrend.vendor.allinspection.camera.manager;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;

/**
 * Created by ly on 2020/4/26 15:56
 */
public class CameraManager {

    private static final int CAMERA_SUPORT_ERROR = -1;
    private static final int CAMERA_1 = 1;
    private static final int CAMERA_2 = 2;

    private static CameraManager mCameraManager = null;

    public static CameraManager getInstance() {
        if (mCameraManager == null) {
            synchronized (CameraManager.class) {
                if (mCameraManager == null) {
                    mCameraManager = new CameraManager();
                }
            }
        }
        return mCameraManager;
    }

    /**
     * LEGACY：设备在较旧的Android设备上以向后兼容模式运行，并且功能非常有限。有限的设备代表基线功能集，也可能包含FULL子集的附加功能（可以理解为包含camera的功能）。
     * FULL：设备还支持传感器，闪光灯，镜头和每帧手动控制，以及高速率的图像捕获。
     * LEVEL_3：设备还支持YUV重新处理和RAW图像捕获，以及其他输出流配置。
     * EXTERNAL：设备类似于有限设备，例如某些传感器或镜头信息未重新排列或帧稳定性较差。
     * <p>
     * ERROR：-1
     *
     * @param ctx
     * @return
     */
    private int getCameraService(Context ctx) {
        android.hardware.camera2.CameraManager manager = (android.hardware.camera2.CameraManager) ctx.getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics("0");//主攝
            return characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return CAMERA_SUPORT_ERROR;
    }

    /**
     * 支持camera的模式
     * @param ctx
     * @return camera1   camera2
     */
    private int getSuportCameraAPI(Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return CAMERA_2;
        }
        return CAMERA_1;
    }
}
