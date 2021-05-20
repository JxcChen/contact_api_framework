package com.contact.step;



import com.contact.api_object.ApiLoader;
import com.contact.global.GlobalVariables;
import com.contact.utils.AssertUtils;
import com.contact.utils.CustomStrUtils;
import com.contact.utils.FakeUtils;
import com.contact.utils.PlaceholderUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * ClassName: TestcaseStepModel
 * date: 2020/12/30 20:24
 * @author JJJJ
 * Description: 用例具体测试步骤
 */
public class TestcaseStepModel {
    // 创建testcase_yaml 文件对应属性


    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private HashMap<String,String> save;
    private HashMap<String,String> saveGlobal;
    // 需要创建assertModel
    private ArrayList<AssertModel> asserts;
    private int id;



    // 需要创建一个 stepResult 承接执行后返回的结果 里面需要封装响应 断言列表 用例变量
    private StepResultModel stepResultModel = new StepResultModel();
    private HashMap<String,String> stepVariables = new HashMap<>();
    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private ArrayList<Executable> assertList = new ArrayList<>();
    private AssertExpectModel expectModel = new AssertExpectModel();
    // 用于储存请求参数 作用于断言

    Logger logger = LoggerFactory.getLogger(TestcaseStepModel.class);

    public StepResultModel run(HashMap<String,String> testcaseVariable, HashMap<Integer, HashMap<String, String>> queryParamMap){
        // 1、更新用例变量  时间戳
        testcaseVariable.put("getTimeStamp", FakeUtils.getTimeMillis());
        logger.info("更新用例变量 新增: getTimeStamp");

        // 1 先将实参中存在的占位符进行替换  时间戳
        if (actualParameter != null){
            // 先清空再存储 避免后续测试受影响
            finalActualParameter.clear();
            logger.info("进行基本参数替换 时间戳 随机数");
            for (String actualParam: actualParameter
            ) {
                if (actualParam.contains("${random")){
                    // 获取随机数位数
                    Integer len = CustomStrUtils.getNum(actualParam);
                    String random = FakeUtils.getRandom(len);
                    // 将获取到的随机数进行储存 后续进行实参替换
                    testcaseVariable.put("random"+len,random);
                    logger.info("生成"+len+"位的随机数: "+random+"");
                }
            }
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter,testcaseVariable));
        }

        // 2 通过ApiLoader 获取所有用例文件 在根据 api 和 action 拿到对应action执行run方法获取响应
        Response response = ApiLoader.run(api, action).run(finalActualParameter,queryParamMap,id);

        // 3 存储变量
        // 先存储局部变量
        if (save != null){
            save.forEach((variableKey,path) -> {
                // 如果创建部门不成功 无法获取response.path(path) 所以需要先判断
                if (response.path(path) != null){
                    stepVariables.put(variableKey,response.path(path).toString());
                }
            });
        }
        // 存储全局变量
        if (saveGlobal != null){
            saveGlobal.forEach((variableKey,path) ->{
                GlobalVariables.getGlobalVariables().put(variableKey,response.path(path).toString());
            });
        }

        // 4 封装断言列表
        if (asserts != null){
            // 先清空assertList 否则多组数据测试时会有影响
            assertList.clear();
            asserts.forEach(assertModel -> {
                // 进行非空判断
                if (CustomStrUtils.isEmpty(assertModel.getActual())||CustomStrUtils.isEmpty(assertModel.getExpect())){
                    logger.error("断言预期值和实际值不能为空");
                    throw new NullPointerException();
                }
                AssertModel finalAssertModel = new AssertModel();
                // 将用例数据赋值给运行时数据  若在原始用例数据上直接修改 会导致在多组csv参数替换时出现错误数据
                AssertModel.copyProperties(assertModel,finalAssertModel);
                // 获取预期数据 预期数据有可能需要进行参数化替换
                String expect = finalAssertModel.getExpect();

                // 判断是否需要进行参数化替换  参数化替换表示
                if (expect.contains("${csv")){
                    finalAssertModel.setExpect(PlaceholderUtils.resolveString(expect,testcaseVariable));
                }else if (expect.contains("${")) {
                    expectModel.setExpectId01(id);
                    expectModel.setExpectId02(id);
                    // 封装expectModel
                    AssertUtils.getActualExpectFormal(expect,expectModel);
                    // 获取实际预期值
                    String actualExpect = AssertUtils.getActualExpect(expectModel, queryParamMap);
                    finalAssertModel.setExpect(actualExpect);
                }
                // 判断实际值是否为空
                try {
                    response.path(assertModel.getActual());
                }catch (Exception e){
                    logger.error("获取不到实际参数值: "+assertModel.getActual());
                    throw e;
                }
                // 添加断言列表
                assertList.add(() -> {
                    if (finalAssertModel.getMatcher().equals("equalTo")) {
                        assertThat(finalAssertModel.getReason(), response.path(finalAssertModel.getActual()).toString(), equalTo(finalAssertModel.getExpect()));
                    }else if (finalAssertModel.getMatcher().equals("containsString"))
                        assertThat(finalAssertModel.getReason(),response.path(finalAssertModel.getActual()).toString(),containsString(finalAssertModel.getExpect()));
                });

            });
        }
        // 5 封装result
        stepResultModel.setAssertList(assertList);
        stepResultModel.setStepVariables(stepVariables);
        stepResultModel.setResponse(response);
        return stepResultModel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
