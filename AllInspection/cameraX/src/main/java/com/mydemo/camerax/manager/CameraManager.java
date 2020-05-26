package com.mydemo.camerax.manager;

import android.content.Context;
import com.mydemo.camerax.config.size.AspectRatio;
import com.mydemo.camerax.config.size.Size;
import com.mydemo.camerax.config.size.SizeMap;
import com.mydemo.camerax.enums.CameraFace;
import com.mydemo.camerax.enums.CameraSizeFor;
import com.mydemo.camerax.enums.FlashMode;
import com.mydemo.camerax.enums.MediaType;
import com.mydemo.camerax.listener.*;

import java.io.File;

/**
 * The camera manager interface.
 *
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/4/13 22:49
 */
public interface CameraManager {

    /**
     * Initialize camera manager.
     *
     * @param context the context
     */
    void initialize(Context context);

    /**
     * Open camera.
     *
     * @param cameraOpenListener camera open callback
     */
    void openCamera(CameraOpenListener cameraOpenListener);

    /**
     * Whether camera is opened.
     *
     * @return whether camera is opened
     */
    boolean isCameraOpened();

    /**
     * Get current camera face.
     *
     * @return camera face
     */
    @CameraFace
    int getCameraFace();

    /**
     * Switch camera into given face.
     *
     * @param cameraFace camera face
     */
    void switchCamera(@CameraFace int cameraFace);

    /**
     * Set current media type.
     *
     * @param mediaType the media type
     */
    void setMediaType(@MediaType int mediaType);

    /**
     * Set whether the voice enabled
     *
     * @param voiceEnable voice enabled
     */
    void setVoiceEnable(boolean voiceEnable);

    /**
     * Whether the voice is enabled.
     *
     * @return whether voice enabled.
     */
    boolean isVoiceEnable();

    /**
     * Set whether auto focus enabled.
     *
     * @param autoFocus auto focus
     */
    void setAutoFocus(boolean autoFocus);

    /**
     * Whether auto focus
     *
     * @return whether auto focus.
     */
    boolean isAutoFocus();

    /**
     * Set flash mode
     *
     * @param flashMode flash mode
     */
    void setFlashMode(@FlashMode int flashMode);

    /**
     * Get current flash mode
     *
     * @return current flash mode
     */
    @FlashMode int getFlashMode();

    /**
     * Set zoom
     *
     * @param zoom zoom
     */
    void setZoom(float zoom);

    /**
     * Get zoom
     *
     * @return zoom
     */
    float getZoom();

    /**
     * Get max zoom supported by camera
     *
     * @return max zoom supported
     */
    float getMaxZoom();

    /**
     * Set desired size
     *
     * @param expectSize the size
     */
    void setExpectSize(Size expectSize);

    /**
     * Set desired aspect ratio.
     *
     * @param expectAspectRatio the desired aspect ratio
     */
    void setExpectAspectRatio(AspectRatio expectAspectRatio);

    /**
     * Get current size for usage.
     *
     * @param sizeFor the size for
     * @return        the size
     */
    Size getSize(@CameraSizeFor int sizeFor);

    /**
     * Get sizes map from aspect ratio to sizes.
     *
     * @param sizeFor sizes for
     * @return        size map
     */
    SizeMap getSizes(@CameraSizeFor int sizeFor);

    /**
     * Get current aspect ratio.
     *
     * @return aspect ratio
     */
    AspectRatio getAspectRatio();

    /**
     * Set display orientation
     *
     * @param displayOrientation display orientation
     */
    void setDisplayOrientation(int displayOrientation);

    /**
     * Add camera size change callback
     *
     * @param cameraSizeListener camera size callback
     */
    void addCameraSizeListener(CameraSizeListener cameraSizeListener);

    /**
     * Take a picture
     *
     * @param cameraPhotoListener camera capture callback
     */
    void takePicture(CameraPhotoListener cameraPhotoListener);

    /**
     * Set maximum video size in bytes.
     *
     * @param videoFileSize video file size
     */
    void setVideoFileSize(long videoFileSize);

    /**
     * Set maximum video duration in ms.
     *
     * @param videoDuration video duration in ms
     */
    void setVideoDuration(int videoDuration);

    /**
     * Start video record
     *
     * @param file                video file to save
     * @param cameraVideoListener video record callback
     */
    void startVideoRecord(File file, CameraVideoListener cameraVideoListener);

    /**
     * Stop video record
     */
    void stopVideoRecord();

    /**
     * Resume preview
     */
    void resumePreview();

    /**
     * Close camera
     *
     * @param cameraCloseListener camera close callback
     */
    void closeCamera(CameraCloseListener cameraCloseListener);

    /**
     * Release camera, destroy thread etc.
     */
    void releaseCamera();
}
