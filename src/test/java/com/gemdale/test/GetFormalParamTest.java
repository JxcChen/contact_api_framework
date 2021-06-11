package com.gemdale.test;

import com.gemdale.utils.CustomStrUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * author: JJJJ
 * date:2021/6/1 16:51
 * Description: TODO
 */
@Epic("测试用的")
@Feature("测试写的用例")
public class GetFormalParamTest {

    @Test
    @Feature("简单测试")
    void test(){
        System.out.println("简单测试");
        Assertions.assertEquals(1,1);
    }


}
