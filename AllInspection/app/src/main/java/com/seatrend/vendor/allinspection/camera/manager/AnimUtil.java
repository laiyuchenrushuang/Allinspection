package com.seatrend.vendor.allinspection.camera.manager;

import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * Created by ly on 2020/4/26 18:22
 */
public class AnimUtil {
    public static class Rate {
        //旋出的动画，无延迟时间
        public static void startAnimationOut(View view) {
            startAnimationOut(view, 0);

        }

        //旋入的动画，无延迟时间
        public static void startAnimationIn(View view) {
            startAnimationIn(view, 0);
        }

        //旋出的动画
        //delay为动画延迟的时间，单位是毫秒
        public static void startAnimationOut(View view, long delay) {
            RotateAnimation animation = new RotateAnimation(240, 180,
                    view.getWidth() / 2, view.getHeight() / 2);
            animation.setDuration(500);
            animation.setStartOffset(delay);
            animation.setFillAfter(true);
            view.startAnimation(animation);

        }

        //旋入的动画
        //delay为动画延迟的时间，单位是毫秒
        public static void startAnimationIn(View view, long delay) {
            RotateAnimation animation = new RotateAnimation(180, 240,
                    view.getWidth() / 2, view.getHeight() / 2);
            animation.setDuration(500);
            animation.setStartOffset(delay);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }

        public static void startAnimation(View view, long delay, int fromrate, int rate) {
            RotateAnimation animation = new RotateAnimation(fromrate, rate ,
                    view.getWidth() / 2, view.getHeight() / 2);
            animation.setDuration(500);
            animation.setStartOffset(delay);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
    }
}
