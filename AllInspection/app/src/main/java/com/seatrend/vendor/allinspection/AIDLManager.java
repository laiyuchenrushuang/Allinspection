package com.seatrend.vendor.allinspection;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.seatrend.vendor.IInspect;
import com.seatrend.vendor.allinspection.base.Constants;
import kotlin.jvm.Synchronized;

/**
 * Created by ly on 2020/7/3 13:34
 */
public class AIDLManager {
    @SuppressLint("StaticFieldLeak")
    private static AIDLManager aidlManager;
    private IInspect iInspect = null;  //aidl 接口
    private Context mContext = null;  //aidl 接口
    private Boolean threadFlag = true;  //aidl 接口

    public static AIDLManager getIncetance() {
        if (aidlManager == null) {
            synchronized (AIDLManager.class) {
                if (aidlManager == null) {
                    aidlManager = new AIDLManager();
                }
            }
        }
        return aidlManager;
    }

    public AIDLManager bindService(Context context) {
        threadFlag = true;

        bindServiceByAidl(context);

        showLog(" iInspect = " + iInspect);
        if (iInspect == null) {
            registerAidl();
        }
        return this;
    }

    public void unbindService(Context context) {
        if (cnnec != null) {
            context.unbindService(cnnec);
            cnnec = null;
        }
        if (iInspect != null) {
            iInspect = null;
        }
    }


    public IInspect build() {
        return iInspect;
    }


    //绑定环检服务
    @Synchronized
    private void bindServiceByAidl(Context context) {
        if (iInspect == null) {
            Intent intent = new Intent("com.seatrend.vendor.respondMessage");
            intent.setPackage(Constants.APP_PACKAGE.INSTANCE.getYU_TONG_PACKAGE());
            if (cnnec != null) {
                context.bindService(intent, cnnec, Context.BIND_AUTO_CREATE);
            } else { //有时这个cnnec 为空 我真是R
                context.bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        iInspect = IInspect.Stub.asInterface(iBinder);
                        try {
                            // 注册死亡代理
                            if (iInspect != null) {
                                iBinder.linkToDeath(mDeathRecipient, 0);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                }, Context.BIND_AUTO_CREATE);
            }

        }
    }

    private ServiceConnection cnnec = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iInspect = IInspect.Stub.asInterface(iBinder);
            try {
                // 注册死亡代理
                if (iInspect != null) {
                    iBinder.linkToDeath(mDeathRecipient, 0);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    /**
     * 监听Binder是否死亡
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {

        @Override
        public void binderDied() {
            if (iInspect == null) {
                return;
            }
            iInspect.asBinder().unlinkToDeath(this, 0);
            iInspect = null;
            //重新绑定
            bindServiceByAidl(mContext);
        }
    };

    //binder 是否存活状态
    private Boolean checkBinderIsAlive() {
        return iInspect != null && iInspect.asBinder().isBinderAlive();
    }

    private void registerAidl() {
        showLog(" checkBinderIsAlive = " + checkBinderIsAlive());
        if (!checkBinderIsAlive()) {
            new Thread(() -> {
                long time1 = System.currentTimeMillis();
                showLog(" threadFlag = " + threadFlag);
                while (threadFlag) {
                    showLog("" + iInspect);
                    if (iInspect == null) {
                        iInspect = aidlManager.build();
                    } else {
                        showLog("AIDLManager iInspect 建立成功 耗时 " + (System.currentTimeMillis() - time1));
                        break;
                    }
                }

            }).start();
        }
    }

    public void setThreadFlag(boolean threadFlag) {
        showLog("关闭线程");
        this.threadFlag = threadFlag;
    }

    private void showLog(String str) {
        Log.d("lylog", " AIDLManager =  " + str);
    }

}
