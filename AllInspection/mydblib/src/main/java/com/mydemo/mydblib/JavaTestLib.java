package com.mydemo.mydblib;

import java.util.Arrays;

/**
 * Created by ly on 2020/5/21 14:35
 */
public class JavaTestLib {
    public static void main(String[] args) {

        System.out.println(getString("11","22","33"));

    }

    public static String  getString(String ...str){
        return str.toString();
    }
}
