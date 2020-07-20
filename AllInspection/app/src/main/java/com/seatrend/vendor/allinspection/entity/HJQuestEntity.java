package com.seatrend.vendor.allinspection.entity;

import java.util.List;

/**
 * Created by ly on 2020/7/6 13:39
 */
public class HJQuestEntity {
    /**
     * fhxx : 环检需求获取成功
     * fhzt : 200
     * sjnr : {"hjcs":2,"sfjy":false,"zp":[{"dmsml":"VIN","dmz":"H001","sfbp":true,"sfhj":true},{"dmsml":"行驶里程","dmz":"H002","sfbp":true,"sfhj":true},{"dmsml":"行驶证","dmz":"0201","sfbp":true,"sfhj":true},{"dmsml":"左前方","dmz":"0111","sfbp":true,"sfhj":true},{"dmsml":"右后方","dmz":"0112","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H003","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H004","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H005","sfbp":true,"sfhj":true},{"dmsml":"后备箱","dmz":"H006","sfbp":true,"sfhj":true}]}
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
        /**
         * hjcs : 2
         * sfjy : false
         * zp : [{"dmsml":"VIN","dmz":"H001","sfbp":true,"sfhj":true},{"dmsml":"行驶里程","dmz":"H002","sfbp":true,"sfhj":true},{"dmsml":"行驶证","dmz":"0201","sfbp":true,"sfhj":true},{"dmsml":"左前方","dmz":"0111","sfbp":true,"sfhj":true},{"dmsml":"右后方","dmz":"0112","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H003","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H004","sfbp":true,"sfhj":true},{"dmsml":"后处理装置","dmz":"H005","sfbp":true,"sfhj":true},{"dmsml":"后备箱","dmz":"H006","sfbp":true,"sfhj":true}]
         */

        private int hjcs;
        private boolean sfjy;
        private List<ZpBean> zp;

        public int getHjcs() {
            return hjcs;
        }

        public void setHjcs(int hjcs) {
            this.hjcs = hjcs;
        }

        public boolean isSfjy() {
            return sfjy;
        }

        public void setSfjy(boolean sfjy) {
            this.sfjy = sfjy;
        }

        public List<ZpBean> getZp() {
            return zp;
        }

        public void setZp(List<ZpBean> zp) {
            this.zp = zp;
        }

        public static class ZpBean {
            /**
             * dmsml : VIN
             * dmz : H001
             * sfbp : true
             * sfhj : true
             */

            private String dmsm1;
            private String dmz;
            private boolean sfbp;
            private boolean sfhj;

            public String getDmsml() {
                return dmsm1;
            }

            public void setDmsml(String dmsml) {
                this.dmsm1 = dmsml;
            }

            public String getDmz() {
                return dmz;
            }

            public void setDmz(String dmz) {
                this.dmz = dmz;
            }

            public boolean isSfbp() {
                return sfbp;
            }

            public void setSfbp(boolean sfbp) {
                this.sfbp = sfbp;
            }

            public boolean isSfhj() {
                return sfhj;
            }

            public void setSfhj(boolean sfhj) {
                this.sfhj = sfhj;
            }
        }
    }
}
