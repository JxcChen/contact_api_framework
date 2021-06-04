package com.gemdale.utils;

import com.gemdale.step.AssertExpectModel;
import com.gemdale.step.AssertModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


/**
 * @author: JJJJ
 * @date:2021/5/19 16:39
 * @Description: 断言需要使用的方法
 */
public class AssertUtils {
    static Logger logger;

    /**
     * 封装形参的数据储存对象
     * @param testcaseFormalExpect 测试用例的预期结果表达式
     * @param expectModel 形参的数据储存对象
     */
    public static void getActualExpectFormal(String testcaseFormalExpect, AssertExpectModel expectModel){
       int expectDataFlag =  getExpectDataMode(testcaseFormalExpect);
       expectModel.setExpectDataFlag(expectDataFlag);
       String operator = "";
       // 将${}除去
       String newExpect = CustomStrUtils.replaceStr(testcaseFormalExpect);
       if (expectDataFlag == 0){
           expectModel.setExpectKey01(newExpect);
       }else if (expectDataFlag == 1 || expectDataFlag == 3){
           // 预期是一个计算公式
           // 需要获取到具体的运算符
           operator = getOperator(testcaseFormalExpect);

           String[] num01AndNum02 = new String[2];
           // 根据运算符进行切割
           if (!CustomStrUtils.isEmpty(operator)){
               if (operator.equals("*"))
                   num01AndNum02 = newExpect.split("\\*");
               else if (operator.equals("+"))
                   num01AndNum02 = newExpect.split("\\+");
               else
                   num01AndNum02 = newExpect.split(operator);
           }
           // 数值1
           String num01 = num01AndNum02[0];
           // 数值2
           String num02 = num01AndNum02[1];
           // 拆分被除数
           if (num01.contains("=")){
               String[] num01IdAndKey = num01.split("=");
               // 设置数值1 用例id
               expectModel.setExpectId01(Integer.valueOf(num01IdAndKey[0]));
               // 设置数值1 获取实参的key
               expectModel.setExpectKey01(num01IdAndKey[1]);
           }
           if (num02.contains("=")){
               String[] num02IdAndKey = num02.split("=");
               // 设置数值1 用例id
               expectModel.setExpectId02(Integer.valueOf(num02IdAndKey[0]));
               // 设置数值1 获取实参的key
               expectModel.setExpectKey02(num02IdAndKey[1]);
           }
           expectModel.setOperator(operator);

       }else if (expectDataFlag == 2){
           // expect_example:${1-service_name}
           String[] idAndKey = newExpect.split("=");
           expectModel.setExpectId01(Integer.valueOf(idAndKey[0]));
           expectModel.setExpectKey01(idAndKey[1]);
       }

    }

    /**
     * 获取运算符
     * @param formalExpect 预期数据
     * @return 运算符
     */
    public static String getOperator(String formalExpect) {
        if (formalExpect.contains("+"))
            return "+";
        else if (formalExpect.contains("-"))
            return "-";
        else if (formalExpect.contains("*"))
            return "*";
        else if (formalExpect.contains("/"))
            return "/";
        return null;
    }

    /**
     * 获取断言的方式
     * 0 需要获取到当前用例的请求参数
     * 1 表示需要获取到别的用例请求变量进行计算
     * 2 表示需要获取到其他用例的请求变量
     * 3 需要获取到当前用例的请求参数进行计算
     * @param formalExpect 逾期数据的形式参数
     * @return 断言的方式标记
     */
    public static int getExpectDataMode(String formalExpect) {
        if (formalExpect.contains("=")&&isCalculate(formalExpect))
            return 1;
        else if (formalExpect.contains("="))
            return 2;
        else if (isCalculate(formalExpect))
            return 3;
        return 0;
    }


    /**
     * 判断数据是否包含计算符号
     * @param str 需要判断的字符串数据
     */
    public static boolean isCalculate(String str){
        return str.contains("+") || str.contains("-") || str.contains("*") | str.contains("/");
    }

    /**
     * 获取计算结果
     * @param num1 数值1
     * @param num2 数值2
     * @param operator 运算符
     */
    public static Integer getCalculatorResult(Integer num1,Integer num2,String operator){
        if (operator.equals("+"))
            return num1 + num2;
        if (operator.equals("-"))
            return num1 - num2;
        if (operator.equals("*"))
            return num1 * num2;
        if (operator.equals("/") && num2 != 0)
            return num1 / num2;
        logger.error(operator+"非正确运算符或被除数为0");
        return 0;
    }

    /**
     * 获取实际预期值
     * @param expectModel 期望形参数据传输对象
     * @param queryParamMap 期望实参储存集合
     * @return 实际的预期值
     */
    public static String getActualExpect(AssertExpectModel expectModel,HashMap<Integer, HashMap<String,String>> queryParamMap){
        int expectDataFlag = expectModel.getExpectDataFlag();
        String actualExpect = "";
        // 判断预期表达式样式
        Integer expectId01 = expectModel.getExpectId01();
        if (expectDataFlag == 0 || expectDataFlag == 2){
            // 预期表达式 : ${service_name}
            try {
                actualExpect = queryParamMap.get(expectId01).get(expectModel.getExpectKey01());
            }catch (Exception e){
                logger.error("请求参数获取异常,id=" + expectId01 + "的请求参数储存为空");
                throw e;
            }
        }else if (expectDataFlag == 1 || expectDataFlag == 3){
            // 获取 数值1/2 的实际值
            Integer num1;
            Integer num2;
            try {
                num1 = Integer.valueOf(queryParamMap.get(expectId01).get(expectModel.getExpectKey01()));
            }catch (Exception e) {
                logger.error("请求参数获取异常,id=" + expectId01 + "的请求参数储存为空");
                throw e;
            }
            Integer expectId02 = expectModel.getExpectId02();
            try {
                num2 = Integer.valueOf(queryParamMap.get(expectId02).get(expectModel.getExpectKey02()));
            }catch (Exception e){
                logger.error("请求参数获取异常,id=" + expectId02 + "的请求参数储存为空");
                throw e;
            }
            // 计算结果
            actualExpect = getCalculatorResult(num1,num2,expectModel.getOperator()).toString();
        }
        return actualExpect;
    }

    /**
     * 封装断言方式
     * @param assertList 断言列表
     * @param response 请求响应对象
     * @param assertModel 断言对象
     */
    public static void setAssertList(ArrayList<Executable> assertList, Response response, AssertModel assertModel) {
        assertList.add(() -> {
            if (assertModel.getMatcher().equals("equalTo")) {
                assertThat(assertModel.getReason(), response.path(assertModel.getActual()).toString(), equalTo(assertModel.getExpect()));
            }else if (assertModel.getMatcher().equals("containsString"))
                assertThat(assertModel.getReason(), response.path(assertModel.getActual()).toString(),containsString(assertModel.getExpect()));
        });
    }
}
