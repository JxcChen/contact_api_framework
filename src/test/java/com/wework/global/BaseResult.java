package com.wework.global;

import io.restassured.response.Response;

/**
 * ClassName: BaseResult
 * date: 2020/12/31 8:45
 * @author JJJJ
 * Description: 存储响应信息
 */
public class BaseResult {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
