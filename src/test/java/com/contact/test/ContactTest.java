package com.contact.test;

import com.contact.common.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.contact.api_object.ApiLoader;
import com.contact.testcase.TestcaseModel;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author: JJJJ
 * @date:2021/5/17 10:47
 * @Description: 合同系统
 */

@Epic("合同管理系统")
@Feature("客户管理用例集合")
public class ContactTest {

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
        String dirPath = Constant.CUSTOMER_SERVICE_COLLECTIONS_DIR;
        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
        return testcaseModels;
    }
}
