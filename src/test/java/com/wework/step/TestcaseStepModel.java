package com.wework.step;



import com.wework.api_object.ApiLoader;
import com.wework.global.GlobalVariables;
import com.wework.utils.PlaceholderUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * ClassName: TestcaseStepModel
 * date: 2020/12/30 20:24
 * @author JJJJ
 * Description: 用例具体测试步骤
 */
public class TestcaseStepModel {
    // 创建testcase_yaml 文件对应属性
    /*
    steps:
  - api: tokenhelper
    action: getToken
    actualParameter: ["ww5ef451bf006ec894","EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU"]
    saveGlobal:
      accesstoken: access_token
    asserts:
      - actual: errcode
        matcher: equalTo
        expect: 2
        reason: 'getToken错误码校验！'
     */

    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private HashMap<String,String> save;
    private HashMap<String,String> saveGlobal;
    // 需要创建assertModel
    private ArrayList<AssertModel> asserts;

    // 需要创建一个 stepResult 承接执行后返回的结果 里面需要封装响应 断言列表 用例变量
    private StepResultModel stepResultModel = new StepResultModel();
    private HashMap<String,String> stepVariables = new HashMap<>();
    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private ArrayList<Executable> assertList = new ArrayList<>();
    public StepResultModel run(HashMap<String,String> testcaseVariable){
        // 1 先将实参中存在的占位符进行替换  时间戳
        if (actualParameter != null){
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter,testcaseVariable));
        }

        // 2 通过ApiLoader 获取所有用例文件 在根据 api 和 action 拿到对应action执行run方法获取响应
        Response response = ApiLoader.run(api, action).run(finalActualParameter);

        // 3 存储变量
        // 先存储局部变量
        if (save != null){
            save.forEach((variableKey,path) -> {
                stepVariables.put(variableKey,response.path(path).toString());
            });
        }
        // 存储全局变量
        if (saveGlobal !=null){
            saveGlobal.forEach((variableKey,path) ->{
                GlobalVariables.getGlobalVariables().put(variableKey,response.path(path).toString());
            });
        }


        // 4 封装断言列表
        if (asserts != null){
            asserts.forEach(assertModel -> {
                assertList.add(() -> {
                    assertThat(assertModel.getReason(),response.path(assertModel.getActual()),equalTo(assertModel.getExpect()));
                });
            });
        }
        // 5 封装result
        stepResultModel.setAssertList(assertList);
        stepResultModel.setStepVariables(stepVariables);
        stepResultModel.setResponse(response);
        return stepResultModel;

    }



    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }
}
