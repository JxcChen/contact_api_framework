package com.contact.utils;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * author: JJJJ
 * date:2021/5/19 15:15
 * Description: 自定义字符串方法
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

    /**
     * 凭借url
     * @param baseUrl 域名加端口
     * @param runUrl 具体路径
     */
    public static String concatUrl(String baseUrl, String runUrl) {
        if (!isEmpty(baseUrl)){
            runUrl = baseUrl+runUrl;
        }
        return runUrl;
    }

    /**
     * post形式参数 封装formalParam
     * @param body body参数
     * @param formalParam 形参列表
     */
    public static void getFormalParam(String body,ArrayList<String> formalParam){
        if (isEmpty(body)){
            logger.error("body不能为空");
        }
        // 根据,对body进行分割
        if (body.contains(",")){
            String[] bodyArr = body.split(",");
            for (String str : bodyArr){
                if (str.contains("$")){
                    formalParam.add(str.substring(str.indexOf("${")+2,str.indexOf("}")));
                }else{
                    logger.error("body分装错误 未包含$");
                }
            }
        }else {
            if (body.contains("$")) {
                formalParam.add(body.substring(body.indexOf("${") + 2, body.indexOf("}")));
            }else{
                logger.error("body分装错误 未包含$");
            }
        }
    }

    /**
     * get形式参数获取formalParam
     * @param query query参数
     * @param formalParam 需要封装的formalParam列表
     */
    public static void getFormalParam(HashMap<String,String> query, ArrayList<String> formalParam){

        query.forEach((key,value)->{
            formalParam.add(key);
        });
    }

    /**
     * 获取实际body参数
     */
    public static String getActualBody(String runBody){
        StringBuffer resultBody = new StringBuffer();
        resultBody.append("{");
        runBody = runBody.substring(1,runBody.length());
        String[] bodyArr = runBody.split(",");
        for (int i = 0; i < bodyArr.length; i++) {
            if (!bodyArr[i].contains("$")){
                if (resultBody.toString().equals("{")){
                    resultBody.append(bodyArr[i]);
                }else {
                    resultBody.append(","+bodyArr[i]);
                }
            }
        }
        resultBody.append("}");
        return resultBody.toString();
    }
}
