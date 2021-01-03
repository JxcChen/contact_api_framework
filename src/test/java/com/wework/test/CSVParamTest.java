package com.wework.test;

import com.wework.utils.CSVParam;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: wework_api_framework_test
 * Created by JJJJ on 2021-01-03 10:15
 * Description: csv文件读取测试
 */
public class CSVParamTest {
    Logger logger = LoggerFactory.getLogger(CSVParamTest.class);

    @Test
    void csvTest() throws Exception {
        String[][] csvFileData = CSVParam.readCSVFileData("src\\test\\resources\\data\\create_department.csv");
        logger.info("debug");
    }


}
