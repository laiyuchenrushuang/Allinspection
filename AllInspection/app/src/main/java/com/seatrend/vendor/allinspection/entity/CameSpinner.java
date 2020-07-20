package com.seatrend.vendor.allinspection.entity;

import java.util.List;

/**
 * Created by ly on 2020/5/20 16:26

 {
 "sfjy":true,
 "hjcs":"1",
 "zp":[{
 "xtlb":"01",
 "dmlb":"0033",
 "dmz":"001",
 "dmsm1":"车身颜色",
 "sfbp":true,
 "sfyp":false,
 "sfhj":true
 }]
 }



 */
public class CameSpinner {

    /**
     * sfjy : true
     * hjcs : 1
     * zp : [{"xtlb":"01","dmlb":"0033","dmz":"001","dmsm1":"车身颜色","sfbp":true,"sfyp":false,"sfhj":true}]
     */

    private boolean sfjy;
    private String hjcs;
    private List<ZpBean> zp;

    public boolean isSfjy() {
        return sfjy;
    }

    public void setSfjy(boolean sfjy) {
        this.sfjy = sfjy;
    }

    public String getHjcs() {
        return hjcs;
    }

    public void setHjcs(String hjcs) {
        this.hjcs = hjcs;
    }

    public List<ZpBean> getZp() {
        return zp;
    }

    public void setZp(List<ZpBean> zp) {
        this.zp = zp;
    }

    public static class ZpBean {
        /**
         * xtlb : 01
         * dmlb : 0033
         * dmz : 001
         * dmsm1 : 车身颜色
         * sfbp : true
         * sfyp : false
         * sfhj : true
         */

        private String xtlb;
        private String dmlb;
        private String dmz;
        private String dmsm1;
        private boolean sfbp;
        private boolean sfyp;
        private boolean sfhj;

        public String getXtlb() {
            return xtlb;
        }

        public void setXtlb(String xtlb) {
            this.xtlb = xtlb;
        }

        public String getDmlb() {
            return dmlb;
        }

        public void setDmlb(String dmlb) {
            this.dmlb = dmlb;
        }

        public String getDmz() {
            return dmz;
        }

        public void setDmz(String dmz) {
            this.dmz = dmz;
        }

        public String getDmsm1() {
            return dmsm1;
        }

        public void setDmsm1(String dmsm1) {
            this.dmsm1 = dmsm1;
        }

        public boolean isSfbp() {
            return sfbp;
        }

        public void setSfbp(boolean sfbp) {
            this.sfbp = sfbp;
        }

        public boolean isSfyp() {
            return sfyp;
        }

        public void setSfyp(boolean sfyp) {
            this.sfyp = sfyp;
        }

        public boolean isSfhj() {
            return sfhj;
        }

        public void setSfhj(boolean sfhj) {
            this.sfhj = sfhj;
        }
    }
}
