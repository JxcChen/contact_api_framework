package com.gemdale.api_object;

import com.gemdale.action.ApiActionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ClassName: ApiLoader
 * date: 2020/12/30 19:47
 * @author JJJJ
 * Description: 加载目录下所有yaml文件进行反序列化并执行action
 */
public class ApiLoader {

    static Logger logger = LoggerFactory.getLogger(ApiLoader.class);
    private static ArrayList<ApiObjectModel> apiObjectModelList = new ArrayList<>();


    /**
     * 加载目录下所有yaml文件 调用ApiObjectModel.load进行反序列化
     * @param dirPath
     */
    public static void load(String dirPath){
        Arrays.stream(new File(dirPath).list()).forEach(filePath ->{
            ApiObjectModel model = null;
            try {
                model = ApiObjectModel.load(dirPath+"/"+filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            apiObjectModelList.add(model);
        });
    }

    /**
     * 根据apiName和 actionName 获取到对应的action并返回
     * @param apiName 接口Name
     * @param actionName 接口方法Name
     * @return 对应方法
     */
    public static ApiActionModel run(String apiName, String actionName){
        final ApiActionModel[] apiActionModel = new ApiActionModel[1];
        final String[] baseUrl = new String[1];
        // 1、遍历调用ApiObjectModel激活 并根据apiName 和 actionName获取对应action执行run方法
        apiObjectModelList.stream().filter(apiObject ->apiObject.getName().equals(apiName) ).forEach(apiObjectModel -> {
            apiActionModel[0] = apiObjectModel.getActions().get(actionName);
            baseUrl[0] = apiObjectModel.getBaseUrl();
        });

        if (apiActionModel[0] != null){
            // 设置baseUrl
            if (baseUrl[0] != null){
                apiActionModel[0].setBaseUrl(baseUrl[0]);
            }
            return apiActionModel[0];
        }else {
            logger.error("没有找到"+apiName+"对应的"+actionName+"action");
        }
        return null;
    }
}
