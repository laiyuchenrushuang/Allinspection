package com.seatrend.vendor.allinspection.entity;

import java.util.List;

/**
 * Created by ly on 2020/5/18 14:43
 * <p>
 * 安检 ->环检 共享的数据
 */


public class ShareEntity {


    /**
     * bmjyy : 否
     * ccdjrq : 2020-05-06
     * ccrq : 2020-05-06
     * cllx : 2
     * clpp1 : 德国大众
     * clsbdh : L0524866663114
     * csys : 白
     * fdjh : SS-22
     * fdjxh : SS-22
     * fzjg : 川A
     * gcjk : 国产
     * gl : 1.5KW
     * hbdbqk : 优秀
     * hdzk : 5
     * hdzzl : 3158kg
     * hphm : 川A
     * hpzl : A
     * jylsh : 001234567
     * lts : 4
     * pl : 1.5T
     * qpzk : 4
     * qzbfqz : 2020-05-06
     * rlzl : 汽油
     * sfmj : 否
     * sfxny : 否
     * sfzmhm : 51052118885
     * sfzmmc : 身份证
     * syr : 张三
     * syxz : 家用
     * wgbhgyys : []
     * xh : 001234567
     * xnyzl : 否
     * xzqh : 510500
     * yxqz : 2020-05-06
     * zbzl : 3158kg
     * zp : [{"zplj":"/storage/emulated/0/AllInspect/CAMERA_PICTRUE/1591780592360.jpg","zplx":"0113","zpmc":"车辆识别代号照片"},{"zplx":"0157","zpmc":"驾驶人座椅汽车安全带"},{"zplx":"0111","zpmc":"车辆左前方斜视45°照片"},{"zplx":"hj0","zpmc":"环检图片0"},{"zplx":"hj1","zpmc":"环检图片1"},{"zplx":"hj2","zpmc":"环检图片2"},{"zplx":"hj3","zpmc":"环检图片3"},{"zplx":"hj4","zpmc":"环检图片4"}]
     * zqyzl : 5T
     * zsxzqh : 500521
     * zt : 优
     * zzcmc : 德国大众公司
     * zzl : 5T
     */
    private String sfxx;
    private SJNR sjnr;

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

    public static class SJNR {

        private int jklx; //++++++++接口类型
        private String bmjyy;
        private String ccdjrq;
        private String ccrq;
        private String cllx;
        private String clpp1;
        private String clsbdh;
        private String csys;
        private String fdjh;
        private String fdjxh;
        private String fzjg;
        private String gcjk;
        private String gl;
        private String hbdbqk;
        private String hdzk;
        private String hdzzl;
        private String hphm;
        private String hpzl;
        private String jylsh;
        private String lts;
        private String pl;
        private String qpzk;
        private String hpzk;
        private String qzbfqz;
        private String rlzl;
        private String sfmj;
        private String sfxny;
        private String sfzmhm;
        private String sfzmmc;
        private String syr;
        private String syxz;
        private String xh;
        private String xnyzl;
        private String xzqh;
        private String yxqz;
        private String zbzl;
        private String zqyzl;
        private String zsxzqh;
        private String zt;
        private String zzcmc;
        private String zzl;
        private String clxh;
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private String yhsfzhm; // + pda用户身份证信息
        private String hjcs; //环检次数

        private List<ZP> zp;

        public int getJklx() {
            return jklx;
        }

        public void setJklx(int jklx) {
            this.jklx = jklx;
        }

        public String getHjcs() {
            return hjcs;
        }

        public void setHjcs(String hjcs) {
            this.hjcs = hjcs;
        }


        public String getYhsfzhm() {
            return yhsfzhm;
        }

        public void setYhsfzhm(String yhsfzhm) {
            this.yhsfzhm = yhsfzhm;
        }


        public String getHpzk() {
            return hpzk;
        }

        public void setHpzk(String hpzk) {
            this.hpzk = hpzk;
        }

        public String getClxh() {
            return clxh;
        }

        public void setClxh(String clxh) {
            this.clxh = clxh;
        }


        public String getBmjyy() {
            return bmjyy;
        }

        public void setBmjyy(String bmjyy) {
            this.bmjyy = bmjyy;
        }

        public String getCcdjrq() {
            return ccdjrq;
        }

        public void setCcdjrq(String ccdjrq) {
            this.ccdjrq = ccdjrq;
        }

        public String getCcrq() {
            return ccrq;
        }

        public void setCcrq(String ccrq) {
            this.ccrq = ccrq;
        }

        public String getCllx() {
            return cllx;
        }

        public void setCllx(String cllx) {
            this.cllx = cllx;
        }

        public String getClpp1() {
            return clpp1;
        }

        public void setClpp1(String clpp1) {
            this.clpp1 = clpp1;
        }

        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getCsys() {
            return csys;
        }

        public void setCsys(String csys) {
            this.csys = csys;
        }

        public String getFdjh() {
            return fdjh;
        }

        public void setFdjh(String fdjh) {
            this.fdjh = fdjh;
        }

        public String getFdjxh() {
            return fdjxh;
        }

        public void setFdjxh(String fdjxh) {
            this.fdjxh = fdjxh;
        }

        public String getFzjg() {
            return fzjg;
        }

        public void setFzjg(String fzjg) {
            this.fzjg = fzjg;
        }

        public String getGcjk() {
            return gcjk;
        }

        public void setGcjk(String gcjk) {
            this.gcjk = gcjk;
        }

        public String getGl() {
            return gl;
        }

        public void setGl(String gl) {
            this.gl = gl;
        }

        public String getHbdbqk() {
            return hbdbqk;
        }

        public void setHbdbqk(String hbdbqk) {
            this.hbdbqk = hbdbqk;
        }

        public String getHdzk() {
            return hdzk;
        }

        public void setHdzk(String hdzk) {
            this.hdzk = hdzk;
        }

        public String getHdzzl() {
            return hdzzl;
        }

        public void setHdzzl(String hdzzl) {
            this.hdzzl = hdzzl;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public String getHpzl() {
            return hpzl;
        }

        public void setHpzl(String hpzl) {
            this.hpzl = hpzl;
        }

        public String getJylsh() {
            return jylsh;
        }

        public void setJylsh(String jylsh) {
            this.jylsh = jylsh;
        }

        public String getLts() {
            return lts;
        }

        public void setLts(String lts) {
            this.lts = lts;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        public String getQpzk() {
            return qpzk;
        }

        public void setQpzk(String qpzk) {
            this.qpzk = qpzk;
        }

        public String getQzbfqz() {
            return qzbfqz;
        }

        public void setQzbfqz(String qzbfqz) {
            this.qzbfqz = qzbfqz;
        }

        public String getRlzl() {
            return rlzl;
        }

        public void setRlzl(String rlzl) {
            this.rlzl = rlzl;
        }

        public String getSfmj() {
            return sfmj;
        }

        public void setSfmj(String sfmj) {
            this.sfmj = sfmj;
        }

        public String getSfxny() {
            return sfxny;
        }

        public void setSfxny(String sfxny) {
            this.sfxny = sfxny;
        }

        public String getSfzmhm() {
            return sfzmhm;
        }

        public void setSfzmhm(String sfzmhm) {
            this.sfzmhm = sfzmhm;
        }

        public String getSfzmmc() {
            return sfzmmc;
        }

        public void setSfzmmc(String sfzmmc) {
            this.sfzmmc = sfzmmc;
        }

        public String getSyr() {
            return syr;
        }

        public void setSyr(String syr) {
            this.syr = syr;
        }

        public String getSyxz() {
            return syxz;
        }

        public void setSyxz(String syxz) {
            this.syxz = syxz;
        }

        public String getXh() {
            return xh;
        }

        public void setXh(String xh) {
            this.xh = xh;
        }

        public String getXnyzl() {
            return xnyzl;
        }

        public void setXnyzl(String xnyzl) {
            this.xnyzl = xnyzl;
        }

        public String getXzqh() {
            return xzqh;
        }

        public void setXzqh(String xzqh) {
            this.xzqh = xzqh;
        }

        public String getYxqz() {
            return yxqz;
        }

        public void setYxqz(String yxqz) {
            this.yxqz = yxqz;
        }

        public String getZbzl() {
            return zbzl;
        }

        public void setZbzl(String zbzl) {
            this.zbzl = zbzl;
        }

        public String getZqyzl() {
            return zqyzl;
        }

        public void setZqyzl(String zqyzl) {
            this.zqyzl = zqyzl;
        }

        public String getZsxzqh() {
            return zsxzqh;
        }

        public void setZsxzqh(String zsxzqh) {
            this.zsxzqh = zsxzqh;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getZzcmc() {
            return zzcmc;
        }

        public void setZzcmc(String zzcmc) {
            this.zzcmc = zzcmc;
        }

        public String getZzl() {
            return zzl;
        }

        public void setZzl(String zzl) {
            this.zzl = zzl;
        }

        public List<ZP> getZp() {
            return zp;
        }

        public void setZp(List<ZP> zp) {
            this.zp = zp;
        }

        public static class ZP {
            /**
             * zplj : /storage/emulated/0/AllInspect/CAMERA_PICTRUE/1591780592360.jpg
             * zplx : 0113
             * zpmc : 车辆识别代号照片
             */

            private String zplj;
            private String zplx;
            private String zpmc;

            public String getZplj() {
                return zplj;
            }

            public void setZplj(String zplj) {
                this.zplj = zplj;
            }

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
        }

    }
}
