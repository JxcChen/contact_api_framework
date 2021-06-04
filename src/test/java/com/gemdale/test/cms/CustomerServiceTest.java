package com.gemdale.test.cms;

import com.gemdale.common.Constant;
import com.gemdale.testcase.TestcaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gemdale.api_object.ApiLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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
 * Project: wework_api_framework_test
 * Created by JJJJ on 2021-01-02 10:53
 * Description:
 */

@Epic("合同管理系统")
@Feature("客户管理用例")
public class CustomerServiceTest {
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);


    @Description("客户服务测试用例")
    @ParameterizedTest
    @MethodSource
    void customerServiceTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


    public static ArrayList<Arguments> customerServiceTest(){
        ApiLoader.load(Constant.CMS_API_DIR);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = Constant.CUSTOMER_SERVICE_TESTCASE_DIR;
        Arrays.stream(new File(dirPath).list()).forEach(file ->{
            try {
                TestcaseModel testcaseModel = TestcaseModel.load(dirPath+"/"+file);
                testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return testcaseModels;
    }
}
