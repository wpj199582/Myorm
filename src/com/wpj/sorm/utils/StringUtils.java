package com.wpj.sorm.utils;

/**
 * @author wangpj
 * @create 2018-07-22 11:40
 * 字符串相关操作
 */
public class StringUtils {
    public static String firstChartoUpper(String str){
        if (Character.isUpperCase(str.charAt(0)))
            return str;
        return (new StringBuffer()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    public static void main(String[] args) {
        System.out.println(firstChartoUpper("abDFE"));
    }
}
