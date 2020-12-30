package com.wework.test;

import com.wework.action.ApiActionModel;
import com.wework.api_object.ApiLoader;
import com.wework.api_object.ApiObjectModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ClassName: ApiActionModelTest
 * date: 2020/12/30 12:41
 * @author JJJJ
 * Description: ApiActionModel单元测试
 */
public class ApiLoderTest {
    Logger logger = LoggerFactory.getLogger(ApiLoderTest.class);

    @Test
    void test() throws IOException {
        ArrayList<String> actParamList = new ArrayList<>();
        // 企业ID ： wwbcc92e0afe51b09e  secret ： MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI  agentId ： 3010084
        actParamList.add("wwbcc92e0afe51b09e");
        actParamList.add("MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI");
        ApiLoader.load("src\\test\\resources\\api");
        ApiActionModel actionModel = ApiLoader.run("tokenhelper", "getToken");
        Response response = actionModel.run(actParamList);
        logger.info("debug");
    }

}
