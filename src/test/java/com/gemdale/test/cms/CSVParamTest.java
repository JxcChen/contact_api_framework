package com.gemdale.test.cms;

import com.gemdale.api_object.ApiLoader;
import com.gemdale.testcase.TestcaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Project: wework_api_framework_test
 * Created by JJJJ on 2021-01-03 10:15
 * Description: csv文件读取测试
 */
public class CSVParamTest {
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @Description("客户服务测试用例集合")
    @ParameterizedTest
    @MethodSource
    void apiTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }

    public static ArrayList<Arguments> apiTest() throws Exception {
        ApiLoader.load("src/test/resources/api");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = "src/test/resources/testcase/contact/customer_service/add_customer_service_csv.yaml";
        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
        return testcaseModels;
    }


}
