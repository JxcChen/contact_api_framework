package com.gemdale.test.mdm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gemdale.api_object.ApiLoader;
import com.gemdale.common.Constant;
import com.gemdale.testcase.TestcaseModel;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
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
public class BuildingTest {
    Logger logger = LoggerFactory.getLogger(BuildingTest.class);

    @Description("房产相关测试用例")
    @ParameterizedTest
    @MethodSource
    void buildingTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


    public static ArrayList<Arguments> buildingTest(){
        ApiLoader.load(Constant.MDM_API_DIR);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = Constant.BUILDING_TESTCASE_DIR;
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
     * 获取楼栋详情
     */
//    public static ArrayList<Arguments> buildingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/building/get_version_list.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取楼栋信息
     */
//    public static ArrayList<Arguments> buildingTest() throws Exception {
//    ApiLoader.load(Constant.MDM_API_DIR);
//    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//    ArrayList<Arguments> testcaseModels = new ArrayList<>();
//    String dirPath = "src/test/resources/testcase/mdm/building/get_building_list.yaml";
//    TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//    testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//    return testcaseModels;
//    }

    /**
     * 新建楼栋
     */
//    public static ArrayList<Arguments> buildingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/building/add_building.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 编辑楼栋
     */
//    public static ArrayList<Arguments> buildingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/building/update_building.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 修改房产状态
     */
//    public static ArrayList<Arguments> buildingTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/building/change_building_status.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }



}
