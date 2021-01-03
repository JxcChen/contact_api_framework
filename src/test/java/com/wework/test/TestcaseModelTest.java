package com.wework.test;

import com.wework.api_object.ApiLoader;
import com.wework.step.AssertModel;
import com.wework.step.StepResultModel;
import com.wework.step.TestcaseStepModel;
import com.wework.testcase.TestcaseModel;
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
public class TestcaseModelTest {
    Logger logger = LoggerFactory.getLogger(TestcaseModelTest.class);
    static ArrayList<String> actParamList = new ArrayList<>();
    @BeforeAll
    static void before(){

        ApiLoader.load("src\\test\\resources\\api");
    }

    @Test
    void test() throws IOException {


        TestcaseModel testcaseModel = TestcaseModel.load("src\\test\\resources\\testcase\\creatdepartment.yaml");
        testcaseModel.run();

        logger.info("debug");


    }

}
