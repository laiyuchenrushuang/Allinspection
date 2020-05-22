package com.seatrend.environment.inspection.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.seatrend.environment.inspection.GsonUtils;
import com.seatrend.environment.inspection.entity.CameSpinner;
import com.seatrend.vendor.IInspect;
import com.seatrend.vendor.ServiceLisener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ly on 2020/4/22 9:50
 */
public class AIDLService extends Service {


    private boolean threadRun = true;

    public static ServiceLisener mServiceLisener = null; //静态的目的缓存起来   [如果不用静态变量来缓存数据 怎么调用这个services的实例 ?]


    //RemoteCallbackList是专门用于删除跨进程listener的接口，它是一个泛型，支持管理任意的AIDL接口
    private RemoteCallbackList<ServiceLisener> mListener = new RemoteCallbackList<>();


    private Binder binder = new IInspect.Stub() {
        @Override
        public void registerListener(ServiceLisener s) {
            showLog("服务器注册成功");
            mServiceLisener = s;
            mListener.register(s);
        }

        @Override
        public void unRegisterListener(ServiceLisener s) {
            if (mServiceLisener != null) {
                showLog("服务器解除成功");
                mServiceLisener = null;
            }
            mListener.unregister(s);
        }

        @Override
        public void sendVehInfo(final String sendJsonData, final ServiceLisener servicelisener) {
            try {
//
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        try {
//                            servicelisener.serviceSuccess("服务器数据版本 1.2\n" +
//                                    "查询 " + sendJsonData + " 的环检信息成功\n" +
//                                    "车辆数据信息：...\n"
//                                    + "车辆照片信息：...\n"
//                                    + "其他信息：...");
//                        } catch (RemoteException e) {
//                            try {
//                                servicelisener.serviceError("服务<<-sendVehInfo异常 " + e.getMessage());
//                            } catch (RemoteException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                    }
//                }, 3000);
                List<CameSpinner> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    CameSpinner entity = new CameSpinner();
                    entity.setDmz("hj" + i);
                    entity.setSfhj(true);
                    entity.setSfyp(false);
                    entity.setSfbp(true);
                    entity.setDmsm1("环检图片" + i);
                    list.add(entity);
                }
                servicelisener.serviceSuccess(GsonUtils.toJson(list));
                showLog("环检图片 1.2 = "+ GsonUtils.toJson(list));
                showLog("环检图片 1.2 = "+ sendJsonData);

            } catch (Exception e) {
                e.printStackTrace();
                showLog("环检图片 Exception"+ e.getMessage());
                try {
                    servicelisener.serviceError("服务<<-sendVehInfo异常 " + e.getMessage());
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void sendDataToServer(String sendJsonData, ServiceLisener servicelisener) {

        }

        @Override
        public void getResultByServer(String sendJsonData, ServiceLisener servicelisener) {

            showLog("getResultByServer");
            sendBackClient(sendJsonData);
        }
    };

    private void sendBackClient(String sendJsonData) {
        int n = mListener.beginBroadcast();
        for (int i = 0; i < n; i++) {
            ServiceLisener l = mListener.getBroadcastItem(i);
            if (l != null) {
                try {
                    showLog("自动发送数据 成功");
                    l.serviceSuccess("" + sendJsonData);//服务端通过这个向客户端发送消息
                    break;
                } catch (RemoteException e) {
                    showLog("自动发送数据 失败");
                    e.printStackTrace();
                }
            } else {
                showLog("自动发送数据 service = null");
            }
        }
        showLog("自动发送数据 service 没找到");
        mListener.finishBroadcast();
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        startThread();
    }

    //服务器定时向客户端发送数据

    private void startThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int count = 0;
                while (threadRun) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    goBackClient(count);
                    if (count == 20) {
                        threadRun = false; //测试20s就够了
                        break;
                    }
                }
            }
        }.start();
    }

    private void goBackClient(int count) {
        int n = mListener.beginBroadcast();
        for (int i = 0; i < n; i++) {
            ServiceLisener l = mListener.getBroadcastItem(i);
            if (l != null) {
                try {
                    showLog("自动发送数据 成功");
                    l.serviceError("" + count);//服务端通过这个向客户端发送消息
                } catch (RemoteException e) {
                    showLog("自动发送数据 失败");
                    e.printStackTrace();
                }
            } else {
                showLog("自动发送数据 service = null");
            }
        }
        showLog("自动发送数据 service 没找到");
        mListener.finishBroadcast();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        threadRun = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        showLog("binder OK ");
        return binder;
    }

    public void showLog(Object msg) {
        Log.d("lylog", "------" + msg + "");
    }


    //服务器向client 发送数据
    public void sendMsgToClient(String strS) {
        showLog("服务数目: servicelisener : " + (mServiceLisener == null));

    }

    //静态的 这个不建议广泛使用 但是也是一种方式 owiner
    public static ServiceLisener getService() {
        return mServiceLisener;
    }


}
