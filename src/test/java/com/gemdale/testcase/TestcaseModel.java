package com.gemdale.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gemdale.step.StepResultModel;

import com.gemdale.step.TestcaseStepModel;
import com.gemdale.utils.CSVParam;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * ClassName: TestcaseModel
 * date: 2020/12/31 12:41
 * @author JJJJ
 * Description:  定义ApiTestCaseModel类，用来用来存储testcase yaml反序列化出来的TestCase实体对象
 */
public class TestcaseModel {

    Logger logger = LoggerFactory.getLogger(TestcaseModel.class);

    private String name;
    private String description;
    private ArrayList<TestcaseStepModel> steps;
    private String csvParameter;

    // 用例变量
    private HashMap<String, String> testcaseVariables = new HashMap<>();
    // 软断言集合
    private ArrayList<Executable> assertAllList = new ArrayList<>();
    private String[][] csvFileData;
    private HashMap<Integer,HashMap<String,String>> queryParamMap = new HashMap<>();
    /**
     * 对yaml文件进行反序列化
     *
     * @param path yaml文件路径
     * @return 反序列化后的TestcaseModel对象
     * @throws IOException
     */
    public static TestcaseModel load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(path), TestcaseModel.class);
    }

    public void run() throws Exception {

        // 处理CSV文件数据
        if (csvParameter != null) {
            // 获取到csv中文件数据
            csvFileData = CSVParam.readCSVFileData(csvParameter);
        }

        // 2、遍历step 执行run方法
        steps.forEach(step -> {
            ArrayList<String> actualParameter = step.getActualParameter();
            // 判断actualParameter中是否包含csv 若包含则代表需要用到csv中的数据
            if (actualParameter != null && actualParameter.size() > 0 && actualParameter.toString().contains("csv")) {
                // 遍历csv数据 并将数据添加到用例变量中
                // 数据组数决定了需要运行的次数
                Arrays.stream(csvFileData).forEach(data -> {
                    for (int i = 0; i < data.length; i++) {
                        // 将数据以 ${csv0} : data 的形式存入
                        String value = data[i];
                        if (value.contains("`")) {
                            value  = value.replaceAll("`", ",");
                        }
                            testcaseVariables.put("csv" + i, value);

                    }
                    StepResultModel stepResultModel = step.run(testcaseVariables,queryParamMap);
                    // 3、处理结果变量
                    if (stepResultModel.getStepVariables() != null && stepResultModel.getStepVariables().size() > 0) {
                        testcaseVariables.putAll(stepResultModel.getStepVariables());
                        logger.info("更新用例变量 新增：" + stepResultModel.getStepVariables());
                    }
                    // 处理断言集合
                    if (stepResultModel.getAssertList() != null && stepResultModel.getAssertList().size() > 0)
                        assertAllList.addAll(stepResultModel.getAssertList());
                });
            } else {
                StepResultModel stepResultModel = step.run(testcaseVariables,queryParamMap);

                // 3、处理结果变量
                if (stepResultModel.getStepVariables() != null && stepResultModel.getStepVariables().size() > 0) {
                    testcaseVariables.putAll(stepResultModel.getStepVariables());
                    logger.info("更新用例变量 新增：" + stepResultModel.getStepVariables());
                }

                // 处理断言集合
                if (stepResultModel.getAssertList() != null && stepResultModel.getAssertList().size() > 0)
                    assertAllList.addAll(stepResultModel.getAssertList());
            }
        });
        // 执行全局断言
        assertAll("全局断言", assertAllList.stream());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<TestcaseStepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<TestcaseStepModel> steps) {
        this.steps = steps;
    }

    public String getCsvParameter() {
        return csvParameter;
    }

    public void setCsvParameter(String csvParameter) {
        this.csvParameter = csvParameter;
    }
}
