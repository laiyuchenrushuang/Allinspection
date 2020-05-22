package com.seatrend.vendor.allinspection.entity;

import java.util.List;

/**
 * Created by ly on 2020/5/18 14:43
 */


/**

 {"jscs":{
 "cphm":"川A 458T6",
 "hplb":"车辆号牌",
 "cllx":"小型汽车",
 "syr":"张XX",
 "zz":"高新区两江国际一栋10-1006",
 "syxz":"家用",
 "clpp":"大众",
 "clxh":"ZN5024XZHW1G4",
 "clsbdh":"LSW913L2250002627",
 "fdjhm":"SS-2250002627",
 "clzcrq":"2010-10-01",
 "zdzzl":"2134kg",
 "hdzk":"5",
 "zbzl":"3158kg",
 "hdzzl":"500kg",
 "gcjk":"国产",
 "fdjxh":"SS-22",
 "pl":"1.5T",
 "edgl":"220kw/h",
 "rl":"汽油",
 "clscqy":"上海一汽公司",
 "clccrq":"2012-10-08",
 "dpscs":"德国大众",
 "czzjmc":"身份证",
 "czsfzhm":"51052119900210071X"
 },
 "photo_list":[{
 "zplx":"A",
 "zpmc":"VIN",
 "zplj":"sss"
 }]
 }


 */
public class ShareEntity {

    /**
     * jscs : {"cphm":"川A 458T6","hplb":"车辆号牌","cllx":"小型汽车","syr":"张XX","zz":"高新区两江国际一栋10-1006","syxz":"家用","clpp":"大众","clxh":"ZN5024XZHW1G4","clsbdh":"LSW913L2250002627","fdjhm":"SS-2250002627","clzcrq":"2010-10-01","zdzzl":"2134kg","hdzk":"5","zbzl":"3158kg","hdzzl":"500kg","gcjk":"国产","fdjxh":"SS-22","pl":"1.5T","edgl":"220kw/h","rl":"汽油","clscqy":"上海一汽公司","clccrq":"2012-10-08","dpscs":"德国大众","czzjmc":"身份证","czsfzhm":"51052119900210071X"}
     * photo_list : [{"zplx":"A","zpmc":"VIN","zplj":"sss"}]
     */

    private JscsBean jscs;
    private List<PhotoListBean> photo_list;

    public JscsBean getJscs() {
        return jscs;
    }

    public void setJscs(JscsBean jscs) {
        this.jscs = jscs;
    }

    public List<PhotoListBean> getPhoto_list() {
        return photo_list;
    }

    public void setPhoto_list(List<PhotoListBean> photo_list) {
        this.photo_list = photo_list;
    }

    public static class JscsBean {
        @Override
        public String toString() {
            return
                    " 车牌号码:'" + cphm + '\'' +
                    "\n 号牌类别:'" + hplb + '\'' +
                    "\n 车辆类型:'" + cllx + '\'' +
                    "\n 所有人:'" + syr + '\'' +
                    "\n 住址:'" + zz + '\'' +
                    "\n 使用性质:'" + syxz + '\'' +
                    "\n 车辆品牌:'" + clpp + '\'' +
                    "\n 车辆型号:'" + clxh + '\'' +
                    "\n 车辆识别码:'" + clsbdh + '\'' +
                    "\n 发动机号码:'" + fdjhm + '\'' +
                    "\n 车辆注册日期:'" + clzcrq + '\'' +
                    "\n 核定载客:'" + zdzzl + '\'' +
                    "\n 最大总质量:'" + hdzk + '\'' +
                    "\n 整备质量:'" + zbzl + '\'' +
                    "\n 核定载质量:'" + hdzzl + '\'' +
                    "\n 国产/进口:'" + gcjk + '\'' +
                    "\n 发动机型号:'" + fdjxh + '\'' +
                    "\n 排量:'" + pl + '\'' +
                    "\n 额定功率:'" + edgl + '\'' +
                    "\n 燃料第一种类:'" + rl + '\'' +
                    "\n 车辆生产企业:'" + clscqy + '\'' +
                    "\n 车辆出厂日期:'" + clccrq + '\'' +
                    "\n 底盘生厂商:'" + dpscs + '\'' +
                    "\n 车主证件名称:'" + czzjmc + '\'' +
                    "\n 车主身份证明号码:'" + czsfzhm + '\'' ;
        }

        /**
         * cphm : 川A 458T6
         * hplb : 车辆号牌
         * cllx : 小型汽车
         * syr : 张XX
         * zz : 高新区两江国际一栋10-1006
         * syxz : 家用
         * clpp : 大众
         * clxh : ZN5024XZHW1G4
         * clsbdh : LSW913L2250002627
         * fdjhm : SS-2250002627
         * clzcrq : 2010-10-01
         * zdzzl : 2134kg
         * hdzk : 5
         * zbzl : 3158kg
         * hdzzl : 500kg
         * gcjk : 国产
         * fdjxh : SS-22
         * pl : 1.5T
         * edgl : 220kw/h
         * rl : 汽油
         * clscqy : 上海一汽公司
         * clccrq : 2012-10-08
         * dpscs : 德国大众
         * czzjmc : 身份证
         * czsfzhm : 51052119900210071X
         */

        private String cphm;
        private String hplb;
        private String cllx;
        private String syr;
        private String zz;
        private String syxz;
        private String clpp;
        private String clxh;
        private String clsbdh;
        private String fdjhm;
        private String clzcrq;
        private String zdzzl;
        private String hdzk;
        private String zbzl;
        private String hdzzl;
        private String gcjk;
        private String fdjxh;
        private String pl;
        private String edgl;
        private String rl;
        private String clscqy;
        private String clccrq;
        private String dpscs;
        private String czzjmc;
        private String czsfzhm;

        public String getCphm() {
            return cphm;
        }

        public void setCphm(String cphm) {
            this.cphm = cphm;
        }

        public String getHplb() {
            return hplb;
        }

        public void setHplb(String hplb) {
            this.hplb = hplb;
        }

        public String getCllx() {
            return cllx;
        }

        public void setCllx(String cllx) {
            this.cllx = cllx;
        }

        public String getSyr() {
            return syr;
        }

        public void setSyr(String syr) {
            this.syr = syr;
        }

        public String getZz() {
            return zz;
        }

        public void setZz(String zz) {
            this.zz = zz;
        }

        public String getSyxz() {
            return syxz;
        }

        public void setSyxz(String syxz) {
            this.syxz = syxz;
        }

        public String getClpp() {
            return clpp;
        }

        public void setClpp(String clpp) {
            this.clpp = clpp;
        }

        public String getClxh() {
            return clxh;
        }

        public void setClxh(String clxh) {
            this.clxh = clxh;
        }

        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getFdjhm() {
            return fdjhm;
        }

        public void setFdjhm(String fdjhm) {
            this.fdjhm = fdjhm;
        }

        public String getClzcrq() {
            return clzcrq;
        }

        public void setClzcrq(String clzcrq) {
            this.clzcrq = clzcrq;
        }

        public String getZdzzl() {
            return zdzzl;
        }

        public void setZdzzl(String zdzzl) {
            this.zdzzl = zdzzl;
        }

        public String getHdzk() {
            return hdzk;
        }

        public void setHdzk(String hdzk) {
            this.hdzk = hdzk;
        }

        public String getZbzl() {
            return zbzl;
        }

        public void setZbzl(String zbzl) {
            this.zbzl = zbzl;
        }

        public String getHdzzl() {
            return hdzzl;
        }

        public void setHdzzl(String hdzzl) {
            this.hdzzl = hdzzl;
        }

        public String getGcjk() {
            return gcjk;
        }

        public void setGcjk(String gcjk) {
            this.gcjk = gcjk;
        }

        public String getFdjxh() {
            return fdjxh;
        }

        public void setFdjxh(String fdjxh) {
            this.fdjxh = fdjxh;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        public String getEdgl() {
            return edgl;
        }

        public void setEdgl(String edgl) {
            this.edgl = edgl;
        }

        public String getRl() {
            return rl;
        }

        public void setRl(String rl) {
            this.rl = rl;
        }

        public String getClscqy() {
            return clscqy;
        }

        public void setClscqy(String clscqy) {
            this.clscqy = clscqy;
        }

        public String getClccrq() {
            return clccrq;
        }

        public void setClccrq(String clccrq) {
            this.clccrq = clccrq;
        }

        public String getDpscs() {
            return dpscs;
        }

        public void setDpscs(String dpscs) {
            this.dpscs = dpscs;
        }

        public String getCzzjmc() {
            return czzjmc;
        }

        public void setCzzjmc(String czzjmc) {
            this.czzjmc = czzjmc;
        }

        public String getCzsfzhm() {
            return czsfzhm;
        }

        public void setCzsfzhm(String czsfzhm) {
            this.czsfzhm = czsfzhm;
        }
    }

    public static class PhotoListBean {
        /**
         * zplx : A
         * zpmc : VIN
         * zplj : sss
         */

        private String zplx;
        private String zpmc;
        private String zplj;

        public String getZplx() {
            return zplx;
        }

        public void setZplx(String zplx) {
            this.zplx = zplx;
        }

        public String getZpmc() {
            return zpmc;
        }

        public void setZpmc(String zpmc) {
            this.zpmc = zpmc;
        }

        public String getZplj() {
            return zplj;
        }

        public void setZplj(String zplj) {
            this.zplj = zplj;
        }
    }

}
