package com.example.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 给一个数字，以固定的位数返回此数字
 * @Author elvis
 * @Date 2021/7/5 14:01
 */
public class DigitsUtil {

    public static void main(String[] args){
        int number = 1;

        /**
         * 0 代表前面补 0
         * 4 代表数字的位数，或者长度为 4
         * d 代表参数为正整数
         */
        String str = String.format("%04d", number);
        System.out.println(str);
        String s = "01";
        int num = Integer.parseInt(s);
        System.out.println(num + 1);
        String st = "5.00";
        BigDecimal bigDecimal = new BigDecimal(st);
//        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bigDecimal.toPlainString());
    }
}
