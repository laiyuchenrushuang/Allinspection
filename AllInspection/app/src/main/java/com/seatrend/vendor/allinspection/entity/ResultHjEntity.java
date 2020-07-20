package com.seatrend.vendor.allinspection.entity;

/**
 * Created by ly on 2020/6/10 15:40
 */
public class ResultHjEntity {

    /**
     * clsbdh : 01
     * jylsh : 01
     * hplb :
     * hphm : 川A 12345
     * jysj : 01
     * hjcs : 01
     * jyjl : A
     */
    private String fhxx;
    private int fhzt;
    private SjnrBean sjnr;


    public String getFhxx() {
        return fhxx;
    }

    public void setFhxx(String fhxx) {
        this.fhxx = fhxx;
    }

    public int getFhzt() {
        return fhzt;
    }

    public void setFhzt(int fhzt) {
        this.fhzt = fhzt;
    }

    public SjnrBean getSjnr() {
        return sjnr;
    }

    public void setSjnr(SjnrBean sjnr) {
        this.sjnr = sjnr;
    }


    public static class SjnrBean {
        private String clsbdh;
        private String jylsh;
        private String hplb;
        private String hphm;
        private String jysj;
        private String hjcs;
        private String jyjl;

        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getJylsh() {
            return jylsh;
        }

        public void setJylsh(String jylsh) {
            this.jylsh = jylsh;
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

        public String getJysj() {
            return jysj;
        }

        public void setJysj(String jysj) {
            this.jysj = jysj;
        }

        public String getHjcs() {
            return hjcs;
        }

        public void setHjcs(String hjcs) {
            this.hjcs = hjcs;
        }

        public String getJyjl() {
            return jyjl;
        }

        public void setJyjl(String jyjl) {
            this.jyjl = jyjl;
        }
    }
}
