package com.seatrend.vendor.allinspection;


import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.seatrend.vendor.allinspection.camera.ui.CameraActivity;
import com.seatrend.vendor.allinspection.utils.StringUtils;
import net.sf.json.xml.XMLSerializer;
import org.apache.xalan.xsltc.util.IntegerArray;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ly on 2020/4/8 14:47
 */
public class JavaTest {
    private static int i = 0;

    public static void main(String[] args) {


//        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><mobilegate><timestamp>232423423423</timestamp><txn>Transaction</txn><amt>0</amt></mobilegate>\n";
        String xmlStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:end=\"http://endpoint.webservice.pda.seatrend.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <end:queryObjectOut>\n" +
                "         <!--Optional:-->\n" +
                "         <xtlb>18</xtlb>\n" +
                "         <!--Optional:-->\n" +
                "         <jkxlh>123</jkxlh>\n" +
                "         <!--Optional:-->\n" +
                "         <jkid>18W07</jkid>\n" +
                "         <!--Optional:-->\n" +
                "         <cjsqbh></cjsqbh>\n" +
                "         <!--Optional:-->\n" +
                "         <dwjgdm></dwjgdm>\n" +
                "         <!--Optional:-->\n" +
                "         <dwmc></dwmc>\n" +
                "         <!--Optional:-->\n" +
                "         <yhbz></yhbz>\n" +
                "         <!--Optional:-->\n" +
                "         <yhxm></yhxm>\n" +
                "         <!--Optional:-->\n" +
                "         <zdbs>127.0.0.1</zdbs>\n" +
                "         <!--Optional:-->\n" +
                "         <QueryXmlDoc>\n" +
                "         <![CDATA[<root><QueryCondition><jyjgbh>5100000114</jyjgbh></QueryCondition></root>\n" +
                "]]>\n" +
                "         </QueryXmlDoc>\n" +
                "      </end:queryObjectOut>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

//        XMLSerializer xmlSerializer = new XMLSerializer();
//        String result = xmlSerializer.read(xmlStr).toString();

//
//        System.out.println(CAMERA_MODEL.MODEL_4_3.getValue());
//        System.out.println(CAMERA_MODEL.MODEL_16_9.getValue());


        //thread c
        Thread c = new Thread() {
            @Override
            public void run() {
                super.run();
                for (; ; ) {
                    showLog(Thread.currentThread().toString() + "  " + i++);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        //thread b
        Thread b = new Thread() {
            @Override
            public void run() {
                super.run();
                for (; ; ) {
                    showLog(Thread.currentThread().toString() + "  " + i++);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        //thread a
        Thread a = new Thread() {
            @Override
            public void run() {
                super.run();
                for (; ; ) {
                    showLog(Thread.currentThread().toString() + "  " + i++);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
//        Lock lock = new ReentrantLock();
//        Object obj = new Object();
//        MyRunable mr = null;
//        for (int i = 0; i < 5; i++) {
//            synchronized (obj) {
//                lock.lock();
//                mr = new MyRunable(i, lock);
//                new Thread(mr).start();
//            }
//        }

//        MyThread mt = new MyThread();
//        MyThread mt1 = new MyThread();
//        new Thread(mt).start();
//        new Thread(mt1).start();
//        new Thread(mr).start();
//        new Thread(mr).start();
//        new Thread(mr).start();

//        new Thread(mt).start();

//        SyncThread syncThread = new SyncThread(1);
//        SyncThread syncThread1 = new SyncThread(2);
//        Thread thread1 = new Thread(syncThread, "SyncThread1");
//        Thread thread2 = new Thread(syncThread1, "SyncThread2");
//        Thread thread3 = new Thread(syncThread, "SyncThread3");
//        Thread thread4 = new Thread(syncThread1, "SyncThread4");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

        System.out.println(SystemClock.uptimeMillis());
        System.out.println(System.currentTimeMillis());

    }

    /**
     * 同步线程
     */

    static class SyncThread implements Runnable {

        private int count;

        public SyncThread(int count) {
            this.count = count;
        }

        public void run() {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + (count++));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public int getCount() {
            return count;
        }
    }


    static Object object = new Object();
    static Object object1 = new Object();
    static Object object2 = new Object();
    static Object object3 = new Object();
    static Integer integer = new Integer(10);
    static int ticket = 20;
    static int ticket1 = 0;


    static class MyThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    if (ticket < 1) {
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showLog(Thread.currentThread().getName() + " Thread  [-] " + "当前票剩 ：" + ticket--);
                }

                synchronized (object1) {
                    if (ticket1 > 11) {
                        break;
                    }
                    showLog(Thread.currentThread().getName() + " Thread  [+] 当前票剩 ：" + ticket1++);
                }
            }
        }
    }

    static class MyRunable extends Thread implements Runnable {
        private int i = 0;
        private Lock lock = new ReentrantLock();

        public MyRunable(int i, Lock lock) {
            this.i = i;
            this.lock = lock;
        }

        @Override
        public  void run() {
            synchronized (lock){
                for (int i = 0; i < 50; i++) {
                    showLog(Thread.currentThread().getName() + "   " + i);
                }
            }
        }
    }

    public static void showLog(Object msg) {
        System.out.println("  " + msg);
    }

    enum CAMERA_MODEL {
        @SuppressLint("DefaultLocale") MODEL_4_3(Float.valueOf(String.format("%.1f", 4f / 3))),
        @SuppressLint("DefaultLocale") MODEL_16_9(Float.valueOf(String.format("%.1f", 16f / 9)));
        private float value;

        CAMERA_MODEL(float value) {

            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }
//    public static String xml2json(String response) {
//        JSONObject jsonObj = null;
//        try {
//            jsonObj = XML.toJSONObject(response);
//        } catch (JSONException e) {
//            Log.e("JSON exception", e.getMessage());
//            e.printStackTrace();
//        }
//        return jsonObj.toString();
//    }

}
