package com.seatrend.vendor.allinspection.entity;

/**
 * Created by ly on 2020/4/8 13:19
 */
public class CommonResponse {

    public String url; //接口
    public String responseString; //响应信息

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }
}
