package com.gemdale.step;

import com.gemdale.global.BaseResult;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName: StepResultModel
 * date: 2020/12/31 8:44
 * @author JJJJ
 * Description: 封装请求结果信息  断言 变量 响应
 */
public class StepResultModel extends BaseResult {
    // 用于存放断言数据
    private ArrayList<Executable> assertList;
    // 用例变量
    private HashMap<String,String> stepVariables;


    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }
}
