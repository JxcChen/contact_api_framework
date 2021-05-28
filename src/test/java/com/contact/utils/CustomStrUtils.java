package com.contact.utils;

import org.slf4j.Logger;

import java.util.regex.Pattern;

/**
 * @author: JJJJ
 * @date:2021/5/19 15:15
 * @Description: 自定义字符串方法
 */
public class CustomStrUtils {
    private static Logger logger;

    /**
     * 切割字符串 获取前两个分割后的数据
     * @param originalStr 需要被分割的数据
     * @param separator 分隔符
     * @param value1 数据1储存变量
     * @param value2 数据2储存变量
     */
    public static void getSplitData(String originalStr,String separator,String value1,String value2){

        if (isEmpty(originalStr)){
            logger.error("需被分割的字符串不能为空");
            return;
        }
        if (!originalStr.contains(separator)){
            logger.error("需被分割的字符串不包含"+separator+"分隔符");
            return;
        }
        String[] splitStr = originalStr.split(separator);
        value1 = splitStr[0];
        value2 = splitStr[1];
    }

    /**
     * 去除字符串中的${}
     * @param originalStr 需要修改的字符串
     * @return 修改后的字符串
     */
    public static String replaceStr(String originalStr){
        return originalStr.replace("${","").replace("}","");
    }

    /**
     * 判断字符串是否为空
     * @param str 需要判空字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str){
        return str == null || str.equals("");
    }

    /**
     * 获取字符串中的数组
     * @param str 包含数字的字符串
     * @return
     */
    public static Integer getNum(String str) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Integer num = Integer.valueOf(pattern.matcher(str).replaceAll(""));
        return num;
    }

    public static String concatUrl(String baseUrl, String runUrl) {
        if (!isEmpty(baseUrl)){
            runUrl = baseUrl+runUrl;
        }
        return runUrl;
    }
}
