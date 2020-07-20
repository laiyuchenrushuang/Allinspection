package com.seatrend.vendor.allinspection.entity;

/**
 * Created by ly on 2020/6/10 15:02
 *
 * 安检-> 请求 send 数据
 */
public class SendHJEntity {
    public String getSfxx() {
        return sfxx;
    }

    public void setSfxx(String sfxx) {
        this.sfxx = sfxx;
    }

    public SJNR getSjnr() {
        return sjnr;
    }

    public void setSjnr(SJNR sjnr) {
        this.sjnr = sjnr;
    }

    /**
     * clsbdh : 01
     * hplb :
     * hphm : 川A 12345
     * xh : 01
     * jyjgbh : 01
     * RLZL : A
     * SFXNY :
     * XNYZL :
     */
    private String sfxx;
    private SJNR sjnr;

    public static class SJNR {
        private String clsbdh;
        private String hplb;
        private String hphm;
        private String xh;
        private String jyjgbh;
        private String rlzl;
        private String sfxny;
        private String xnyzl;
        private String jylb;  //检验类别
        private String yxqz;//+++有效起止
        private int jklx;//+++接口类型 (1查询  2获取需求  3调用小工具)

        public int getJklx() {
            return jklx;
        }

        public void setJklx(int jklx) {
            this.jklx = jklx;
        }

        public String getYxqz() {
            return yxqz;
        }

        public void setYxqz(String yxqz) {
            this.yxqz = yxqz;
        }

        public String getJylb() {
            return jylb;
        }

        public void setJylb(String jylb) {
            this.jylb = jylb;
        }


        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getHplb() {
            return hplb;
        }

        public void setHplb(String hplb) {
            this.hplb = hplb;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public String getXh() {
            return xh;
        }

        public void setXh(String xh) {
            this.xh = xh;
        }

        public String getJyjgbh() {
            return jyjgbh;
        }

        public void setJyjgbh(String jyjgbh) {
            this.jyjgbh = jyjgbh;
        }

        public String getRLZL() {
            return rlzl;
        }

        public void setRLZL(String RLZL) {
            this.rlzl = RLZL;
        }

        public String getSFXNY() {
            return sfxny;
        }

        public void setSFXNY(String SFXNY) {
            this.sfxny = SFXNY;
        }

        public String getXNYZL() {
            return xnyzl;
        }

        public void setXNYZL(String XNYZL) {
            this.xnyzl = XNYZL;
        }
    }
}
