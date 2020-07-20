package com.seatrend.vendor.allinspection.utils;

import com.seatrend.vendor.allinspection.entity.TestEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ly on 2020/5/26 13:20
 */
public class SortUtil {

    /**
     * @param data  数据源
     * @return 新的序列
     */

    public static ArrayList<TestEntity> compareToList(ArrayList<TestEntity> data) {


        Collections.sort(data, new Comparator<TestEntity>() {
            @Override
            public int compare(TestEntity o1, TestEntity o2) {

                if (o1.getSfbp() == o2.getSfbp()) {
                    if(Integer.valueOf(o1.getGrade())>Integer.valueOf(o2.getGrade())){
                        return 1;
                    }else {
                        return -1;
                    }

                }else {
                    if(!o1.getSfbp()){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            }
        });

        return data;
    }
}
