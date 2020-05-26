package com.mydemo.camerax.preview.impl;

import android.content.Context;
import android.view.ViewGroup;
import com.mydemo.camerax.config.size.Size;
import com.mydemo.camerax.preview.CameraPreview;
import com.mydemo.camerax.preview.CameraPreviewCallback;

/**
 * Base camera preview.
 *
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/4/13 22:53
 */
abstract class BaseCameraPreview implements CameraPreview {

    private int width;

    private int height;

    private CameraPreviewCallback cameraPreviewCallback;

    BaseCameraPreview(Context context, ViewGroup parent) {
    }

    @Override
    public void setCameraPreviewCallback(CameraPreviewCallback cameraPreviewCallback) {
        this.cameraPreviewCallback = cameraPreviewCallback;
    }

    void notifyPreviewAvailable() {
        if (cameraPreviewCallback != null) {
            cameraPreviewCallback.onAvailable(this);
        }
    }

    @Override
    public boolean isAvailable() {
        return width > 0 && height > 0;
    }

    @Override
    public Size getSize() {
        return Size.of(width, height);
    }

    void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
