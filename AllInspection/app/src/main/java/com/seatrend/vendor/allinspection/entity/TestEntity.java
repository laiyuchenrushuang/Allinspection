package com.seatrend.vendor.allinspection.entity;

/**
 * Created by ly on 2020/5/26 13:22
 */

public class TestEntity implements Comparable<TestEntity> {
    public TestEntity(String grade, String sex, String score) {
        this.grade = grade;
        this.sex = sex;
        this.score = score;
    }

    private String grade;
    private String sex;
    private String score;
    private Boolean sfbp;

    public TestEntity(String grade, String sex, String score, Boolean sfbp) {
        this.grade = grade;
        this.sex = sex;
        this.score = score;
        this.sfbp = sfbp;
    }

    public Boolean getSfbp() {
        return sfbp;
    }

    public void setSfbp(Boolean sfbp) {
        this.sfbp = sfbp;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    @Override
    public int compareTo(TestEntity o) {

        if (this.getSfbp() == o.getSfbp()) {
            return Integer.valueOf(this.score)- (Integer.valueOf(o.score));
        }else {
            if(this.getSfbp()){
                return -1;
            }else {
                return 1;
            }
        }
    }
}
