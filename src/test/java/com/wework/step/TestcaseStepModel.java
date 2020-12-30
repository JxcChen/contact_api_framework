package com.wework.step;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<AssertModel> assertModelList;

}
