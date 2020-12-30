package com.wework.test;

import com.wework.action.ApiActionModel;
import com.wework.global.GlobalVariables;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName: ApiActionModelTest
 * date: 2020/12/30 12:41
 * @author JJJJ
 * Description: ApiActionModel单元测试
 */
public class ApiActionModelTest {
    Logger logger = LoggerFactory.getLogger(ApiActionModelTest.class);

    @Test
    void test(){
        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setGet("https://qyapi.weixin.qq.com/cgi-bin/${url}");

        HashMap<String,String> globalVariables = new HashMap<>();
        globalVariables.put("url","gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        HashMap<String,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");
        apiActionModel.setQuery(query);

        ArrayList<String> formalParam = new ArrayList<>();
        formalParam.add("corpid");
        formalParam.add("corpsecret");
        apiActionModel.setFormalParam(formalParam);

        ArrayList<String> actParamList = new ArrayList<>();
        // 企业ID ： wwbcc92e0afe51b09e  secret ： MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI  agentId ： 3010084
        actParamList.add("wwbcc92e0afe51b09e");
        actParamList.add("MmNdXbFeCNiPJEztv1Kd1TqW6e3Gy6BBgPVRWDJa9fI");

        apiActionModel.run(actParamList);
        logger.info("debug");
    }

}
