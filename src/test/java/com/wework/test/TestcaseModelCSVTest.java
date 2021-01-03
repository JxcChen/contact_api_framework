package com.wework.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wework.api_object.ApiLoader;
import com.wework.testcase.TestcaseModel;
import com.wework.testcase.TestcaseModelCSV;
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
public class TestcaseModelCSVTest {
    Logger logger = LoggerFactory.getLogger(TestcaseModelCSVTest.class);
    static ArrayList<String> actParamList = new ArrayList<>();
    @BeforeAll
    static void before(){

        ApiLoader.load("src\\test\\resources\\api");
    }

    @Test
    void testCSV() throws Exception {

        TestcaseModelCSV testcaseModelCSV = TestcaseModelCSV.load("src/test/resources/testcase/creatdepartment_csv.yaml");
        testcaseModelCSV.run();
        logger.info("debug");
    }

    @ParameterizedTest
    @MethodSource
    void apiTest(TestcaseModelCSV testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


    public static ArrayList<Arguments> apiTest(){
        ApiLoader.load("src/test/resources/api");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = "src/test/resources/testcase";
        Arrays.stream(new File(dirPath).list()).forEach(file ->{
            try {
                TestcaseModelCSV testcaseModel = TestcaseModelCSV.load(dirPath+"/"+file);
                testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return testcaseModels;
    }

}
