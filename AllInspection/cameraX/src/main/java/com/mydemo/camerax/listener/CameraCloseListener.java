package com.mydemo.camerax.listener;


import com.mydemo.camerax.enums.CameraFace;

/**
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/4/17 22:47
 */
public interface CameraCloseListener {

    void onCameraClosed(@CameraFace int cameraFace);
}
