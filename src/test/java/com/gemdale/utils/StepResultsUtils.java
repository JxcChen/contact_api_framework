package com.gemdale.utils;

import com.gemdale.step.StepResultModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: JJJJ
 * @date:2021/6/2 9:49
 * @Description: TODO
 */
public class StepResultsUtils {
    /**
     * 封装执行结果
     * @param stepResultModel 执行结果数据封装对象
     * @param assertList 断言数据
     * @param stepVariables 执行参数
     * @param response 响应
     */
    public static void setStepResults(StepResultModel stepResultModel, ArrayList<Executable> assertList, HashMap<String,String> stepVariables, Response response){
        stepResultModel.setAssertList(assertList);
        stepResultModel.setStepVariables(stepVariables);
        stepResultModel.setResponse(response);
    }
}
