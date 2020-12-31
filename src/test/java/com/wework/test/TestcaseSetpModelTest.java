package com.wework.test;

import com.wework.action.ApiActionModel;
import com.wework.api_object.ApiLoader;
import com.wework.step.AssertModel;
import com.wework.step.StepResultModel;
import com.wework.step.TestcaseStepModel;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName: ApiActionModelTest
 * date: 2020/12/30 12:41
 * @author JJJJ
 * Description: ApiActionModel单元测试
 */
public class TestcaseSetpModelTest {
    Logger logger = LoggerFactory.getLogger(TestcaseSetpModelTest.class);
    static ArrayList<String> actParamList = new ArrayList<>();
    @BeforeAll
    static void before(){

        // 企业ID ： wwbcc92e0afe51b09e  secret ： MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI  agentId ： 3010084
        actParamList.add("wwbcc92e0afe51b09e");
        actParamList.add("MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI");
        ApiLoader.load("src\\test\\resources\\api");
    }

    @Test
    void test() throws IOException {

        /*
        steps:
          - api: tokenhelper
            action: getToken
            actualParameter: ["ww5ef451bf006ec894","EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU"]
            saveGlobal:
              accesstoken: access_token
            asserts:
              - actual: errcode
                matcher: equalTo
                expect: 0
                reason: 'getToken错误码校验01！'
              - actual: errcode
                matcher: equalTo
                expect: 2
                reason: 'getToken错误码校验01！'
         */
        TestcaseStepModel testcaseStepModel = new TestcaseStepModel();
        testcaseStepModel.setAction("getToken");

        testcaseStepModel.setActualParameter(actParamList);

        testcaseStepModel.setApi("tokenhelper");

        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("0");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01");
        ArrayList<AssertModel> assertList = new ArrayList<>();
        assertList.add(assertModel);
        testcaseStepModel.setAsserts(assertList);

        HashMap<String,String> save = new HashMap<>();
        save.put("accesstoken","access_token");
        testcaseStepModel.setSave(save);
        testcaseStepModel.setSaveGlobal(save);

        HashMap<String,String> stepVariable = new HashMap<>();
        StepResultModel resultModel = testcaseStepModel.run(stepVariable);

        logger.info("debug");


    }

}
