package com.mydemo.camerax.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.mydemo.camerax.enums.PreviewViewType.SURFACE_VIEW;
import static com.mydemo.camerax.enums.PreviewViewType.TEXTURE_VIEW;

/**
 * Camera preview view type.
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2019-12-28 11:26
 */
@IntDef(value = {SURFACE_VIEW, TEXTURE_VIEW})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PreviewViewType {

    /**
     * {@link android.view.SurfaceView} will be used
     */
    int SURFACE_VIEW    = 0;

    /**
     * {@link android.view.TextureView} will be used
     */
    int TEXTURE_VIEW    = 1;
}
