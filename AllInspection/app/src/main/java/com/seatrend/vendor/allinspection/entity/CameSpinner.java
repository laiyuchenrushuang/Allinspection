package com.seatrend.vendor.allinspection.entity;

/**
 * Created by ly on 2020/5/20 16:26
 */
public class CameSpinner {
    private String xtlb;
    private String dmlb;
    private String dmz;
    private String dmsm1;
    private String dmsm2;
    private String dmsm3;
    private Boolean sfbp;
    private Boolean sfyp;
    private Boolean sfhj =false; //是否是环检 默认false   true代表是环检的照片列表信息

    public Boolean getSfhj() {
        return sfhj;
    }

    public void setSfhj(Boolean sfhj) {
        this.sfhj = sfhj;
    }
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

    public String getDmsm2() {
        return dmsm2;
    }

    public void setDmsm2(String dmsm2) {
        this.dmsm2 = dmsm2;
    }

    public String getDmsm3() {
        return dmsm3;
    }

    public void setDmsm3(String dmsm3) {
        this.dmsm3 = dmsm3;
    }

    public Boolean getSfbp() {
        return sfbp;
    }

    public void setSfbp(Boolean sfbp) {
        this.sfbp = sfbp;
    }

    public Boolean getSfyp() {
        return sfyp;
    }

    public void setSfyp(Boolean sfyp) {
        this.sfyp = sfyp;
    }
}
