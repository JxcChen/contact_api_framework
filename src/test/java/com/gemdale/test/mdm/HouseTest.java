package com.gemdale.test.mdm;

import com.gemdale.api_object.ApiLoader;
import com.gemdale.common.Constant;
import com.gemdale.testcase.TestcaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
 * @author: JJJJ
 * @date:2021/5/26 11:59
 * @Description: TODO
 */
@Epic("基础数据平台")
@Feature("房产相关用例")
public class HouseTest {
    Logger logger = LoggerFactory.getLogger(HouseTest.class);

    @Description("房产相关测试用例")
    @ParameterizedTest
    @MethodSource
    void houseTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


    public static ArrayList<Arguments> houseTest(){
        ApiLoader.load(Constant.MDM_API_DIR);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = Constant.HOUSE_TESTCASE_DIR;
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
    /**
     * 获取房产详情
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/get_space_detail.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取房屋下所有客户信息
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//    ApiLoader.load(Constant.MDM_API_DIR);
//    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//    ArrayList<Arguments> testcaseModels = new ArrayList<>();
//    String dirPath = "src/test/resources/testcase/mdm/house/get_cust_list.yaml";
//    TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//    testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//    return testcaseModels;
//    }

    /**
     * 导出房产数据
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/export_house_data.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取房产版本信息
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/get_version_list.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取房产列表
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/get_parking_lot_list.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 修改房产状态
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/change_lot_status.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }



    /**
     * 导入房产数据
     */
//    public static ArrayList<Arguments> houseTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/upload_house.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }


}
