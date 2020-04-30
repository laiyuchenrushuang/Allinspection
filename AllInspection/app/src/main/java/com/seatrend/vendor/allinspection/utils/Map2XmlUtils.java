package com.seatrend.vendor.allinspection.utils;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Map2XmlUtils {




    public static String Map2Xml_AddTop(String xml) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        sb.append(xml);
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param map
     * @param isQuery---组装模式：true为写入，false为查询
     * @return
     */
    public static String Map2Xml_Request(Map map, Map basemap, boolean isQuery) {
        String flagName = "QueryCondition";
        if (!isQuery) flagName = "vehispara";
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><root><BaseData>");
        mapToXML(basemap, sb);
        sb.append("</BaseData>");
        //sb.append("<signature></signature>");
        sb.append("<" + flagName + ">");
        mapToXML(map, sb);
        sb.append("</" + flagName + "></root>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param map
     * @param isQuery---组装模式：true为写入，false为查询
     * @return
     */
    public static String Map2Xml_Request(Map map, Map basemap, boolean isQuery, String signdata) {
        String flagName = "QueryCondition";
        if (!isQuery) flagName = "vehcrpara";
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><root><BaseData>");
        mapToXML(basemap, sb);
        sb.append("</BaseData>");
        sb.append("<signature><signdata></signdata><signtime>" + signdata + "</signtime></signature>");
        sb.append("<" + flagName + ">");
        mapToXML(map, sb);
        sb.append("</" + flagName + "></root>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void mapToXML(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML(hm, sb);
                }
                sb.append("</" + key + ">");
            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    try {
                        sb.append("<" + key + ">" + URLEncoder.encode(StringUtils.isNull(value), "utf-8")  + "</" + key + ">");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static String getmapToXML(Map map) {
        StringBuffer sb = new StringBuffer();
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML(hm, sb);
                }
                sb.append("</" + key + ">");
            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    try {
                        sb.append("<" + key + ">" +  URLEncoder.encode(StringUtils.isNull(value), "utf-8") + "</" + key + ">");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return sb.toString();
    }


    public static String str2Xml_Request(String xml) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><root>");
        sb.append(xml);
        sb.append("</root>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
