package com.gemdale.api_object;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gemdale.action.ApiActionModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * ClassName: ApiObjectModel
 * date: 2020/12/30 13:28
 * @author JJJJ
 * Description: api_yaml 映射类
 */
public class ApiObjectModel {
    private String name;
    private HashMap<String , ApiActionModel> actions;
    private String baseUrl;


    /**
     * 对yaml文件进行反序列化
     * @param path 文件路径
     * @return 反序列化后的ApiObjectModel对象
     * @throws IOException
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(path),ApiObjectModel.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
