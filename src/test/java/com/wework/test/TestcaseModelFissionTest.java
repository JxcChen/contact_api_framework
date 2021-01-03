package com.wework.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wework.api_object.ApiLoader;
import com.wework.testcase.TestcaseModel;
import com.wework.testcase.TestcaseModelCSV;
import com.wework.testcase.TestcaseModelFission;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * ClassName: ApiActionModelTest
 * date: 2020/12/30 12:41
 * @author JJJJ
 * Description: ApiActionModel单元测试
 */
public class TestcaseModelFissionTest {
    Logger logger = LoggerFactory.getLogger(TestcaseModelFissionTest.class);
    static ArrayList<String> actParamList = new ArrayList<>();
    @BeforeAll
    static void before(){

        ApiLoader.load("src\\test\\resources\\api");
    }

    @Test
    void testFission() throws Exception {

        TestcaseModelFission testcaseModelCSV = TestcaseModelFission.load("src/test/resources/testcase_fission/creatdepartment_fission.yaml");
        testcaseModelCSV.run();
        logger.info("debug");
    }



}
