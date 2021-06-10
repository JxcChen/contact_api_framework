package com.gemdale.test.mdm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gemdale.api_object.ApiLoader;
import com.gemdale.common.Constant;
import com.gemdale.testcase.TestcaseModel;
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
public class ParkingTest {
    Logger logger = LoggerFactory.getLogger(ParkingTest.class);

    @Description("房产相关测试用例")
    @ParameterizedTest
    @MethodSource
    void parkingTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


//    public static ArrayList<Arguments> parkingTest(){
//        ApiLoader.load(Constant.MDM_API_DIR);
//
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/parking";
//        Arrays.stream(new File(dirPath).list()).forEach(file ->{
//            try {
//                TestcaseModel testcaseModel = TestcaseModel.load(dirPath+"/"+file);
//                testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        return testcaseModels;
//    }
    /**
     * 添加车场
     */
//    public static ArrayList<Arguments> parkingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/parking/add_parking_lot.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 编辑车场
     */
//    public static ArrayList<Arguments> parkingTest() throws Exception {
//    ApiLoader.load(Constant.MDM_API_DIR);
//    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//    ArrayList<Arguments> testcaseModels = new ArrayList<>();
//    String dirPath = "src/test/resources/testcase/mdm/parking/update_parking_lot.yaml";
//    TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//    testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//    return testcaseModels;
//    }

    /**
     * 修改车场状态
     */
//    public static ArrayList<Arguments> parkingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/parking/change_lot_status.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取车位详情
     */
    public static ArrayList<Arguments> parkingTest() throws Exception {
        ApiLoader.load(Constant.MDM_API_DIR);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = "src/test/resources/testcase/mdm/parking/get_space_detail.yaml";
        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
        return testcaseModels;
    }



//    /**
//     * 修改组织状态
//     */
//    public static ArrayList<Arguments> parkingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/organization/change_organization_status.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }



    /**
     * 导入房产数据
     */
//    public static ArrayList<Arguments> parkingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/house/upload_house.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }


}
