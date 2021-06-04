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

import java.util.ArrayList;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author: JJJJ
 * @date:2021/5/26 11:59
 * @Description: TODO
 */
@Epic("基础数据平台")
@Feature("公共接口测试用例")
public class CommonTest {
    Logger logger = LoggerFactory.getLogger(CommonTest.class);

    @Description("公共接口相关测试用例")
    @ParameterizedTest
    @MethodSource
    void commonTest(TestcaseModel testcaseModel, String name, String description) throws Exception {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        testcaseModel.run();
        logger.info("debug");
    }


//    public static ArrayList<Arguments> commonTest(){
//        ApiLoader.load(Constant.MDM_API_DIR);
//
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/common";
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
     * 取消上传数据
     */
//    public static ArrayList<Arguments> commonTest() throws Exception {
//    ApiLoader.load(Constant.MDM_API_DIR);
//    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//    ArrayList<Arguments> testcaseModels = new ArrayList<>();
//    String dirPath = "src/test/resources/testcase/mdm/common/cancle_import_data.yaml";
//    TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//    testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//    return testcaseModels;
//    }

    /**
     * 获取组织下拉框数据
     */
    public static ArrayList<Arguments> commonTest() throws Exception {
        ApiLoader.load(Constant.MDM_API_DIR);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Arguments> testcaseModels = new ArrayList<>();
        String dirPath = "src/test/resources/testcase/mdm/common/get_org_msg.yaml";
        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
        return testcaseModels;
    }

    /**
     * 获取平级省市区下拉框
     */
//    public static ArrayList<Arguments> commonTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/common/get_selector_data.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取美剧下拉列表
     */
//    public static ArrayList<Arguments> commonTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/common/get_dict_detail.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }

    /**
     * 获取城市下拉框列表
     */
//    public static ArrayList<Arguments> commonTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/common/get_city_msg.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }



    /**
     * 批量导入数据
     */
//    public static ArrayList<Arguments> commonTest() throws Exception {
//        ApiLoader.load(Constant.MDM_API_DIR);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ArrayList<Arguments> testcaseModels = new ArrayList<>();
//        String dirPath = "src/test/resources/testcase/mdm/common/import_data.yaml";
//        TestcaseModel testcaseModel = TestcaseModel.load(dirPath);
//        testcaseModels.add(arguments(testcaseModel,testcaseModel.getName(),testcaseModel.getDescription()));
//        return testcaseModels;
//    }


}
