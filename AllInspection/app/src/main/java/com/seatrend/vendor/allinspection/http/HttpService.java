package com.seatrend.vendor.allinspection.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonSyntaxException;
import com.seatrend.vendor.allinspection.MyApplication;
import com.seatrend.vendor.allinspection.base.*;
import com.seatrend.vendor.allinspection.entity.CommonProgress;
import com.seatrend.vendor.allinspection.entity.CommonResponse;
import com.seatrend.vendor.allinspection.utils.*;
//import net.sf.json.xml.XMLSerializer;
import okhttp3.*;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ly on 2020/4/8 11:50
 */
public class HttpService {

    private static HttpService mHttpService;
    private BaseModule mBaseModule;

    private final int SUCCESS_CODE = 2;
    private final int FAILED_CODE = 3;
    private final int PREGRESS_CODE = 4;
    private static int mTimeOut = 60 * 1000;  //耗时操作

    public static HttpService getInstance() {
        if (mHttpService == null) {
            synchronized (HttpService.class) {
                if (mHttpService == null) {
                    mHttpService = new HttpService();
                }
            }
        }
        return mHttpService;
    }


    private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(mTimeOut, TimeUnit.MILLISECONDS)
            .writeTimeout(mTimeOut, TimeUnit.MILLISECONDS)
            .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)  //超时连接20秒
            .build();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_CODE:
                    CommonResponse commonResponse1 = (CommonResponse) msg.obj;
                    mBaseModule.doWorkResults(commonResponse1, true);
                    break;
                case FAILED_CODE:
                    CommonResponse commonResponse2 = (CommonResponse) msg.obj;
                    mBaseModule.doWorkResults(commonResponse2, false);
                    break;
                case PREGRESS_CODE:
                    CommonProgress commonProgress = (CommonProgress) msg.obj;
                    ((ProgressModule)mBaseModule).downloadProgress(commonProgress);
                    break;
            }
        }
    };


    /**
     *
     * @param context
     * @param dataMap map 数据集合
     * @param JKID  接口id
     * @param isQuery  true  = get    false = post
     * @return
     */
    public String request(Context context, Map dataMap, final String JKID, boolean isQuery, BaseModule module) {
        this.mBaseModule = module;
        Message message= new Message();

        try {
            //   SoapObject soapObject = new SoapObject("http://endpoint.webservice.pda.seatrend.com", isQuery ? "queryObjectOut" : "writeObjectOut");
            SoapObject soapObject = new SoapObject("http://localhost:1044/", isQuery ? "queryObjectOut" : "writeObjectOut");
            soapObject.addProperty("xtlb", "");
            soapObject.addProperty("jkxlh","");
            soapObject.addProperty("jkid", JKID);
            soapObject.addProperty("cjsqbh", "");
            soapObject.addProperty("dwjgdm", "");
            soapObject.addProperty("dwmc", "");
            soapObject.addProperty("yhbz", "");
            soapObject.addProperty("yhxm", "");
            soapObject.addProperty("zdbs", "");
            soapObject.addProperty( "xmlDoc", Map2XmlUtils.Map2Xml_Request(dataMap,getBaseMap(context),isQuery));
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = soapObject;
            envelope.dotNet=false;
            HttpTransportSE ht;
            String url= SharedPreferencesUtils.getNetworkAddress();



            ht = new HttpTransportSE(url+ Constants.Companion.getHTTPS_WSDL(), mTimeOut);
            ht.debug = false;
            ht.call("", envelope);
            Object object = envelope.getResponse();
            String result = object.toString();

            try {

//                result = URLDecoder.decode(result, "utf-8");
//                XMLSerializer xmlSerializer = new XMLSerializer();
//                result = xmlSerializer.read(result).toString();

                Log.d("[http]"," result = "+result);
                // 这个地方解析出来，要解析服务器返回的错误
                // ...

                message.what = SUCCESS_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(JKID);
                commonResponse.setResponseString(result);
                message.obj = commonResponse;
                mHandler.sendMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(JKID);
                commonResponse.setResponseString("数据编码异常");
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(JKID);
            commonResponse.setResponseString("数据读写失败");
            message.obj = commonResponse;
            mHandler.sendMessage(message);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(JKID);
            commonResponse.setResponseString("数据xml解析异常");
            message.obj = commonResponse;
            mHandler.sendMessage(message);

        }
        return "";
    }

    public static Map getBaseMap(Context context) {
        Map<String,String> basemap = new HashMap();
        if ("" == null) {
            basemap.put("userid", "0");
            basemap.put("glbm", "0");
            basemap.put("fzjg", "0");
        } else {
            basemap.put("userid", StringUtils.isNull(""));
            basemap.put("glbm", "0");
            basemap.put("fzjg", StringUtils.isNull(""));
        }
        basemap.put("net",new PhoneUtil(context).getNetWorkType());
        basemap.put("version", new PhoneUtil(context).getVersion());
        basemap.put("uptime", StringUtils.longToStringData(SystemClock.uptimeMillis()));
        basemap.put("imei", new PhoneUtil(context).getIMEI());
        basemap.put("imsi", new PhoneUtil(context).getAndroidID());
        basemap.put("phonenum", StringUtils.isNull(new PhoneUtil(context).getPhoneNum()));
        basemap.put("iccid", StringUtils.isNull(new PhoneUtil(context).getPhoneSign(context)));
        basemap.put("lat", "");
        basemap.put("lng", "");
        basemap.put("fwip", "");
        basemap.put("fwdk", "");

        return basemap;
    }

    //上传file



    //下载file



    public void getDataFromServer(Map<String, String> map, final String url, String method, BaseModule module) {
        this.mBaseModule = module;
        if (!NetUtils.isNetworkAvailable(MyApplication.Companion.getMyApplicationContext())) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString("网络异常，请检查网络是否连接");
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();

        final String finalUrl = baseUrl + url;
        Request request;
        try {

            if (method.equals(Constants.Companion.getGET())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("?");
                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    if (Constants.Companion.getAES_ENABLE()){
//                        buffer.append(entry.getKey().trim() + "=" + AESUtils.encrypt(entry.getValue().trim()) + "&");
//                    }else {
                        buffer.append(entry.getKey().trim() + "=" + entry.getValue().trim() + "&");
//                    }
                }
                String s = buffer.toString();
                String parameter = s.substring(0, s.length() - 1);
                //有token验证 ->  解除登录退出和获取码表接口的token验证
                if (true) {
                    request = new Request.Builder()
                            .url(finalUrl + parameter)
                            .get()
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(finalUrl + parameter)
                            .get()
                            .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                            .build();
                }

                Log.i("httpService", finalUrl + parameter);
            } else {

                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    if (Constants.Companion.getAES_ENABLE()){
//                        builder.add(entry.getKey().trim(), AESUtils.encrypt(entry.getValue().trim()));
//                    }else {
                        builder.add(entry.getKey().trim(), entry.getValue().trim());
//                    }
                }
                RequestBody requestBody = builder.build();
                request = new Request.Builder()
                        .url(finalUrl)
                        .post(requestBody)
                        .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                        .build();
                Log.i("httpService", finalUrl);
            }
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                String resp = response.body().string();
                Log.d("httpservice ", url+" result1 = " + resp);
//                if (Constants.Companion.getAES_ENABLE()){
//                    resp=AESUtils.decrypt(resp);
//                }
                Log.d("httpservice ", url+" result2 = " + resp);
                if (TextUtils.isEmpty(resp)) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("服务器响应内容为空");
                    message.obj = commonResponse;
                    mHandler.sendMessage(message);
                    return;
                }
                try {
                    BaseEntity baseEntity = GsonUtils.gson(resp, BaseEntity.class);
                    //虽然响应成功，有可能数据不对
                    boolean status = baseEntity.getStatus();
                    int code = baseEntity.getCode();
                    if (status && code == 0) {
                        message.what = SUCCESS_CODE;
                    } else {
                        message.what = FAILED_CODE;
                    }
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString(resp);
                    message.obj = commonResponse;

                } catch (JsonSyntaxException e) {
                    try {
                        ErrorEntity errorEntity = GsonUtils.gson(resp, ErrorEntity.class);
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException " + errorEntity.toString());
                        message.obj = commonResponse;
                    } catch (JsonSyntaxException e1) {
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString(resp);
                        message.obj = commonResponse;
                    }
                }
                mHandler.sendMessage(message);
            }
        });
    }

    public void getDataFromServerByJson(String json, final String url, BaseModule module) {
        this.mBaseModule = module;
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
        final String finalUrl = baseUrl + url;
//        if (Constants.Companion.getAES_ENABLE()){
//            json=AESUtils.encrypt(json);
//        }
        Request request;
        try {
            MediaType mjson = MediaType.parse("application/json; charset=utf-8");
            RequestBody vehicleTemp = RequestBody.create(mjson, json);

            request = new Request.Builder()
                    .url(finalUrl)
                    .post(vehicleTemp)
                    .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                    .build();
            Log.i("httpService", finalUrl);
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                String resp = response.body().string();
//                if (Constants.Companion.getAES_ENABLE()){
//                    resp=AESUtils.decrypt(resp);
//                }
                if (TextUtils.isEmpty(resp)) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("服务器响应内容为空");
                    message.obj = commonResponse;
                    mHandler.sendMessage(message);
                    return;
                }
                try {
                    BaseEntity baseEntity = GsonUtils.gson(resp, BaseEntity.class);
                    //虽然响应成功，有可能数据不对
                    boolean status = baseEntity.getStatus();
                    int code = baseEntity.getCode();
                    if (status && code == 0) {
                        message.what = SUCCESS_CODE;
                    } else {
                        message.what = FAILED_CODE;
                    }
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString(resp);
                    message.obj = commonResponse;
                } catch (JsonSyntaxException e) {
                    try {
                        ErrorEntity errorEntity = GsonUtils.gson(resp, ErrorEntity.class);
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException " + errorEntity.toString());
                        message.obj = commonResponse;
                    } catch (JsonSyntaxException e1) {
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException" + e1.getMessage());
                        message.obj = commonResponse;
                    }
                }
                mHandler.sendMessage(message);
            }
        });
    }

    public void downLoadFileFromServer(Map<String, String> map, final File file, final String url, String method, BaseModule module) {
        this.mBaseModule = module;
//        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
//        final String finalUrl = baseUrl + url;
        Request request = null;
        try {
            if (method.equals(Constants.Companion.getGET())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("?");
                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    buffer.append(entry.getKey().trim() + "=" + entry.getValue().trim() + "&");
//                    if (Constants.Companion.getAES_ENABLE()){
//                        buffer.append(entry.getKey().trim() + "=" + AESUtils.encrypt(entry.getValue().trim()) + "&");
//                    }else {
                        buffer.append(entry.getKey().trim() + "=" + entry.getValue().trim() + "&");
//                    }
                }
                String s = buffer.toString();
                String parameter = s.substring(0, s.length() - 1);
                request = new Request.Builder()
                        .url(url + parameter)
                        .get()
                        // .addHeader(Constants.QAUTH,User.TOKEN)
                        .build();
                Log.i("httpService", url + parameter);
            } else {
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    if (Constants.Companion.getAES_ENABLE()){
//                        builder.add(entry.getKey().trim(), AESUtils.encrypt(entry.getValue().trim()));
//                    }else {
                        builder.add(entry.getKey().trim(), entry.getValue().trim());
//                    }
                }
                request = new Request.Builder()
                        .url(url)
                        .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                        .post(builder.build())
                        .build();
            }
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }


        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                ResponseBody body = response.body();

                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;

                try {
                    long totalLength = body.contentLength();
//                    if(totalLength<0){
//                        message.what=FAILED_CODE;
//                        CommonResponse commonResponse=new CommonResponse();
//                        commonResponse.setUrl(url);
//                        commonResponse.setResponseString("下载出错，无法下载该文件");
//                        message.obj=commonResponse;
//                        mHandler.sendMessage(message);
//                        return;
//                    }
                    inputStream = body.byteStream();
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    int num = 0;
                    DecimalFormat df = new DecimalFormat("#.0");
                    while ((len = inputStream.read(buffer)) != -1) {
                        num += len;
                        fileOutputStream.write(buffer, 0, len);
                        double d =  (double)num / (double) totalLength;
                        String format = df.format(d*100);
                        Message progressMsg = Message.obtain();
                        CommonProgress commonProgress=new CommonProgress();
                        commonProgress.setProgress(format);
                        commonProgress.setUrl(url);
                        progressMsg.what=PREGRESS_CODE;
                        progressMsg.obj=commonProgress;
                        mHandler.sendMessage(progressMsg);
                    }

                    Message progressMsg = Message.obtain();
                    CommonProgress commonProgress = new CommonProgress();
                    commonProgress.setProgress("100");
                    commonProgress.setUrl(url);
                    progressMsg.what = PREGRESS_CODE;
                    progressMsg.obj = commonProgress;
                    mHandler.sendMessage(progressMsg);
                } catch (Exception e) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("下载出错 " + e.getMessage());
                    message.obj = commonResponse;
                    mHandler.sendMessage(message);
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
                mHandler.sendMessage(message);
            }
        });

    }

    public void downLoadFileFromServer(final String url, String method, BaseModule module) {
        this.mBaseModule = module;
       /* String baseUrl = "http://"+SharedPreferencesUtils.getIpAddress() + ":" + SharedPreferencesUtils.getPort()+Constants.BASEURL;
        final String finalUrl = baseUrl+url;*/
        Request request = null;
        try {
            if (method.equals(Constants.Companion.getGET())) {
                request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
            } else {
                FormBody.Builder builder = new FormBody.Builder();
                request = new Request.Builder()
                        .url(url)
                        .post(builder.build())
                        .build();
            }
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                String body = response.body().string();
                message.what = SUCCESS_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(body);
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }
        });

    }

    public void uploadFileToServer(final String url, File file, Map<String, String> map, BaseModule module) {
        this.mBaseModule = module;
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
        final String finalUrl = baseUrl + url;
        Request request = null;
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            if (file.exists()) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestBody);

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.addFormDataPart(entry.getKey().trim(), entry.getValue().trim());

                }
            }
            request = new Request.Builder()
                    .url(finalUrl)
                    .post(builder.build())
                    .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                    .build();
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }
        Log.i("httpService", finalUrl);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();

                String resp = response.body().string();
//                Log.i("httpService", "result1 解码前【文件】 = " + resp);
//                if (Constants.Companion.getAES_ENABLE()){
//                    resp=AESUtils.decrypt(resp);
//                }
                Log.i("httpService", "result  = " + resp);
                if (TextUtils.isEmpty(resp)) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("【PHOTO】服务器响应内容为空");
                    message.obj = commonResponse;
                    mHandler.sendMessage(message);
                    return;
                }
                try {
                    BaseEntity baseEntity = GsonUtils.gson(resp, BaseEntity.class);
                    //虽然响应成功，有可能数据不对
                    boolean status = baseEntity.getStatus();
                    int code = baseEntity.getCode();
                    if (status && code == 0) {
                        message.what = SUCCESS_CODE;
                    } else {
                        message.what = FAILED_CODE;
                    }
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString(resp);
                    message.obj = commonResponse;
                } catch (JsonSyntaxException e) {
                    try {
                        ErrorEntity errorEntity = GsonUtils.gson(resp, ErrorEntity.class);
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException " + errorEntity.toString());
                        message.obj = commonResponse;
                    } catch (JsonSyntaxException e1) {
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException" + e1.getMessage());
                        message.obj = commonResponse;
                    }
                }
                mHandler.sendMessage(message);
            }
        });

    }

    public void uploadFileToServer(final String url, List<File> fileList, String type, BaseModule module) {
        this.mBaseModule = module;
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
        final String finalUrl = baseUrl + url;
        Request request = null;
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            for (File f : fileList) {
                if (f.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), f);
                    builder.addFormDataPart("file", f.getName(), requestBody);
                    builder.addFormDataPart("type", type);
                }
            }
            request = new Request.Builder()
                    .url(finalUrl)
                    .post(builder.build())
                    .build();
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(e.getMessage());
                message.obj = commonResponse;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                String resp = response.body().string();
                if (TextUtils.isEmpty(resp)) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("服务器响应内容为空");
                    message.obj = commonResponse;
                    mHandler.sendMessage(message);
                    return;
                }
                try {
                    BaseEntity baseEntity = GsonUtils.gson(resp, BaseEntity.class);
                    //虽然响应成功，有可能数据不对
                    boolean status = baseEntity.getStatus();
                    int code = baseEntity.getCode();
                    if (status && code == 0) {
                        message.what = SUCCESS_CODE;
                    } else {
                        message.what = FAILED_CODE;
                    }
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString(resp);
                    message.obj = commonResponse;
                } catch (JsonSyntaxException e) {
                    try {
                        ErrorEntity errorEntity = GsonUtils.gson(resp, ErrorEntity.class);
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException " + errorEntity.toString());
                        message.obj = commonResponse;
                    } catch (JsonSyntaxException e1) {
                        message.what = FAILED_CODE;
                        CommonResponse commonResponse = new CommonResponse();
                        commonResponse.setUrl(url);
                        commonResponse.setResponseString("JsonSyntaxException" + e1.getMessage());
                        message.obj = commonResponse;
                    }
                }
                mHandler.sendMessage(message);
            }
        });
    }

    public void uploadFileByURL(Map<String, String> map, final File file, final String url, String method, BaseModule module) {
        this.mBaseModule = module;
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
        StringBuffer buffer = new StringBuffer();
        buffer.append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            buffer.append(entry.getKey().trim() + "=" + entry.getValue().trim() + "&");
        }
        String s = buffer.toString();
        String parameter = s.substring(0, s.length() - 1);
        final String finalUrl = baseUrl + url + parameter;

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream os = null;
                InputStream is = null;
                try {
                    URL mURL = new URL("http://192.168.0.129:8088/application/printApplication?cjxxbh=cjbh8343874990935552");
                    URLConnection conn = mURL.openConnection();
                    // conn.addRequestProperty();
                    is = conn.getInputStream();
                    int totalLength = conn.getContentLength();
                    byte[] buffer = new byte[1024];
                    int len;
                    int num = 0;
                    os = new FileOutputStream(file);

                    DecimalFormat df = new DecimalFormat("#.0");
                    while ((len = is.read(buffer)) != -1) {
                        num += len;
                        os.write(buffer, 0, len);
                        double d = (double) num / (double) totalLength;
                        String format = df.format(d * 100);
                        Message progressMsg = Message.obtain();
                        CommonProgress commonProgress = new CommonProgress();
                        commonProgress.setProgress(format);
                        commonProgress.setUrl(url);
                        progressMsg.what = PREGRESS_CODE;
                        progressMsg.obj = commonProgress;
                        mHandler.sendMessage(progressMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    /**
     * @param url
     * @param jsonStr
     * @param file
     * @param map
     * @param module  混合数据的post接口
     */
    public void postMutipartData(final String url, String jsonStr, File file, Map<String, String> map, BaseModule module) {
        this.mBaseModule = module;
        String baseUrl = SharedPreferencesUtils.getNetworkAddress();
        final String finalUrl = baseUrl + url;
        Request request = null;
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            if (file != null && file.exists()) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestBody);
            }

            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.addFormDataPart(entry.getKey().trim(), entry.getValue().trim());
                }
            }

            if (jsonStr != null) {
                MediaType mjson = MediaType.parse("application/json; charset=utf-8");
                RequestBody jsBody = RequestBody.create(mjson, jsonStr);
                builder.addPart(jsBody);
            }

            request = new Request.Builder()
                    .url(finalUrl)
                    .post(builder.build())
                    .addHeader(Constants.Companion.getQAUTH(), Constants.Companion.getTOKEN())
                    .build();
        } catch (Exception e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
            return;
        }

        mOkHttpClient.newCall(request).enqueue(new HttpCallback(url));
    }

    private class HttpCallback implements Callback {
        private String url;

        public HttpCallback(String url) {
            this.url = url;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            Message message = Message.obtain();
            message.what = FAILED_CODE;
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setUrl(url);
            commonResponse.setResponseString(e.getMessage());
            message.obj = commonResponse;
            mHandler.sendMessage(message);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Message message = Message.obtain();
            String resp = response.body().string();
            if (TextUtils.isEmpty(resp)) {
                message.what = FAILED_CODE;
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString("服务器响应内容为空");
                message.obj = commonResponse;
                mHandler.sendMessage(message);
                return;
            }
            try {
                BaseEntity baseEntity = GsonUtils.gson(resp, BaseEntity.class);
                //虽然响应成功，有可能数据不对
                boolean status = baseEntity.getStatus();
                int code = baseEntity.getCode();
                if (status && code == 0) {
                    message.what = SUCCESS_CODE;
                } else {
                    message.what = FAILED_CODE;
                }
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setUrl(url);
                commonResponse.setResponseString(resp);
                message.obj = commonResponse;
            } catch (JsonSyntaxException e) {
                try {
                    ErrorEntity errorEntity = GsonUtils.gson(resp, ErrorEntity.class);
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("JsonSyntaxException " + errorEntity.toString());
                    message.obj = commonResponse;
                } catch (JsonSyntaxException e1) {
                    message.what = FAILED_CODE;
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setUrl(url);
                    commonResponse.setResponseString("JsonSyntaxException" + e1.getMessage());
                    message.obj = commonResponse;
                }
            }
            mHandler.sendMessage(message);
        }
    }
}
