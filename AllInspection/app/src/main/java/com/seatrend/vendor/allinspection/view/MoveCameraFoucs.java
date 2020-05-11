package com.seatrend.vendor.allinspection.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.seatrend.vendor.allinspection.R;

/**
 * Created by seatrend on 2018/11/26.
 */

public class MoveCameraFoucs extends View {

    public static final int CAMERA_FOCUS_BACK = 0x1;
    public static final int CAMERA_FOCUS_BACK_TIME = 3000;  //对焦成功后多少秒重置回原点

    private Paint mPaint;
    private Bitmap bitmap;

    private float bitmapX, bitmapY;
    private int bitmapHeight;
    private int bitmapWidth;
    private float degrees = 0;
    private Context mContext;

    private boolean animotionflag = false;//防止多线程
    private boolean animotionflag_focus_back = false;//防止多线程

    public MoveCameraFoucs(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public MoveCameraFoucs(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public MoveCameraFoucs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }


    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.camera_show);
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmapX = getWidth() / 2 - bitmapWidth / 2;
        bitmapY = getHeight() / 2 - bitmapHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(degrees, bitmapX + bitmapWidth / 2, bitmapY + bitmapHeight / 2);
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, mPaint);
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!animotionflag) {
                animotionflag = true;
                new Thread(() -> {
                    animationViewShow();
                    animotionflag = false;
                }).start();
            }
        }
    };

    //对焦动画
    public void animationViewShow() {
        for (int i = 0; i < 30; i++) {
            degrees += 2;
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 30; i++) {
            degrees -= 2;
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        degrees = 0;
    }

    public void resetFocus() {
        bitmapX = getWidth() / 2 - bitmapWidth / 2;
        bitmapY = getHeight() / 2 - bitmapHeight / 2;
//        animotionflag_focus_back = false;
        setNoFocused();
        setVisibility(MoveCameraFoucs.GONE);
        postInvalidate();
    }

    public synchronized void setFocused(Handler handler) {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.camera_focus_show);
        postInvalidate();
        if (!animotionflag_focus_back) {
            animotionflag_focus_back = true;
            Message m = Message.obtain();
            m.what = CAMERA_FOCUS_BACK;
            handler.postDelayed(() -> handler.sendMessage(m), CAMERA_FOCUS_BACK_TIME);
        }
    }


    public synchronized void setFocusFailed(Handler handler) {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.camera_focus_failed);//对焦失败
        postInvalidate();
        if (!animotionflag_focus_back) {
            animotionflag_focus_back = true;
            Message m = Message.obtain();
            m.what = CAMERA_FOCUS_BACK;
            handler.postDelayed(() -> handler.sendMessage(m), CAMERA_FOCUS_BACK_TIME);
        }
    }

    public synchronized void setFocused() {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.camera_focus_show);
        postInvalidate();
    }

    //回初始白圈状态
    public void setNoFocused() {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.camera_show);
        postInvalidate();
    }


    //设置focus的位置
    public void setFocusViewXY(float x, float y) {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.camera_show);
        bitmapX = x - bitmapWidth / 2; //例如 默认+1 所以初始化-1
        bitmapY = y - bitmapHeight / 2;
        Message m = Message.obtain();
        mHandler.sendMessage(m);
    }

    //camera 成功后才可以启动FOUCS动画
    public void setCameraFocusBack(boolean flag) {
        animotionflag_focus_back = flag;
    }
}
