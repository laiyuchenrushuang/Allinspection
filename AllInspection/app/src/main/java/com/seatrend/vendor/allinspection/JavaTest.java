package com.seatrend.vendor.allinspection;


import android.annotation.SuppressLint;
import com.seatrend.vendor.allinspection.entity.TestEntity;
import com.seatrend.vendor.allinspection.utils.Base64Utils;
import com.seatrend.vendor.allinspection.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ly on 2020/4/8 14:47
 */
public class JavaTest {

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

        String newStr="gradle".substring(0, 1).toUpperCase()+"gradle".replaceFirst("\\w","");
        String methodStr="get"+newStr;
//        Method method1 = getClass().getMethod(methodStr, null);

        TestEntity t = new TestEntity("001","boy","99",false);
        TestEntity t1 = new TestEntity("002","girl","88",false);
        TestEntity t2 = new TestEntity("003","boy","89",false);
        TestEntity t3 = new TestEntity("004","boy","77",true);
        TestEntity t4 = new TestEntity("005","girl","50",true);
        TestEntity t43 = new TestEntity("005","girl","65",true);
        TestEntity t41 = new TestEntity("005","boy","53",true);
        TestEntity t42 = new TestEntity("005","boy","54",false);
        TestEntity t5 = new TestEntity("006","girl","65",false);

        ArrayList<TestEntity> list = new ArrayList<>();
        list.add(t4);
        list.add(t41);
        list.add(t42);
        list.add(t43);
        list.add(t2);
        list.add(t1);
        list.add(t);
        list.add(t3);
        list.add(t5);
        Collections.sort(list);
        System.out.println(GsonUtils.toJson(list));
//        list.sort(Comparator.comparing(TestEntity::getSfbp).thenComparing(TestEntity::getGrade).thenComparing(TestEntity::getScore));
        System.out.println("------------------------------");
//        System.out.println(GsonUtils.toJson(list));
//
//        System.out.println(GsonUtils.toJson(SortUtil.compareToList(list)));
//        System.out.println(Integer.valueOf("005"));

        String a = "A009";
        String s = a.replaceAll("[^0-9]","");

        System.out.println(s);

        System.out.println("------------------------------");

        int A = -1;int B = 6;

        float r = (A*1.0f/B)<0?(A*1.0f/B)-0.5f:(A*1.0f/B)+0.5f;
//        float r = ((A+B)/2.0f)/B;
        System.out.println(Base64Utils.base64Decode(Base64Utils.reverse("WDExMzIxMTEyOTkxMjgyMTE0"),""));

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
