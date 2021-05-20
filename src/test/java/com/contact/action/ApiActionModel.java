package com.contact.action;

import com.contact.global.GlobalVariables;
import com.contact.utils.CustomStrUtils;
import com.contact.utils.PlaceholderUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * ClassName: ApiActionModel
 * date: 2020/12/30 8:40
 * @author JJJJ
 * Description: 用来存储接口对象yaml反序列化出来的action单元
 */
public class ApiActionModel {

    // 1 声明yaml_action中对应的属性

    private String url ;
    private String method;
    private String contentType;
    private ArrayList<String> formalParam;
    private HashMap<String,String> headers;
    private String body;
    private String post;
    private HashMap<String,String> query;
    private String get;
    private HashMap<String,String> actionVariables = new HashMap<>();

    public Response run(ArrayList<String> actualParam, HashMap<Integer, HashMap<String, String>> queryParamList, int id){
        // 创建运行时变量
        String runBody = this.body;
        String runUrl = this.url;
        HashMap<String,String> runQuery = new HashMap<>();
        // 确定url和method
        if(post != null){
            runUrl = post;
            method = "post";
        }else if (get != null){
            runUrl = get;
            method = "get";
        }

        // 对可能存在占位符的变量进行全局实参替换
        // 需要创建一个全局变量类 和 替换形参的工具类
        if(runBody != null){
            runBody = PlaceholderUtils.resolveString(runBody, GlobalVariables.getGlobalVariables());
        }
        if (runUrl !=null){
            runUrl = PlaceholderUtils.resolveString(runUrl,GlobalVariables.getGlobalVariables());
        }
        if (query!=null){
            runQuery.putAll(PlaceholderUtils.resolveMap(query,GlobalVariables.getGlobalVariables()));
        }

        // 将形参和实参进行整合到一个map中
        if (formalParam !=null&&formalParam.size()>0&&actualParam!=null&&formalParam.size()==actualParam.size()){

            // 对参数进行遍历
            for (int i = 0; i < formalParam.size(); i++) {
                String param = actualParam.get(i);
                if (param.contains("$")){
                    // 需要进行实参和请求参数建的替换
                    String newParam = CustomStrUtils.replaceStr(param);
                    String[] paramSplit = newParam.split("=");
                    param = queryParamList.get(Integer.valueOf(paramSplit[0])).get(paramSplit[1]);
                }
                actionVariables.put(formalParam.get(i),param);
            }
            // 对可能存在占位符的变量根据用例变量进一步再次进行替换
            if(runBody != null){
                runBody = PlaceholderUtils.resolveString(runBody, actionVariables);
            }
            if (runUrl !=null){
                runUrl = PlaceholderUtils.resolveString(runUrl,actionVariables);
            }
            if (query!=null){
                runQuery.putAll(PlaceholderUtils.resolveMap(query,actionVariables));
            }
        }
        // 存放对应id的请求参数 用于后续断言
        queryParamList.put(id,actionVariables);
        // 根据获取到的请求数据执行请求并获取响应
        RequestSpecification specification = given().log().all();
        if (contentType!=null){
            specification.contentType(contentType);
        }
        if (headers!=null){
            specification.headers(headers);
        }
        if (runQuery!=null){
            specification.formParams(runQuery);
        }
        if (runBody!=null){
            specification.body(runBody);
        }
        Response response = specification.request(method, runUrl).then().log().all().extract().response();
        return response;

    }


    public ArrayList<String> getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(ArrayList<String> formalParam) {
        this.formalParam = formalParam;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }


}
