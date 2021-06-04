package com.gemdale.test;

import com.gemdale.utils.CustomStrUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author: JJJJ
 * @date:2021/6/1 16:51
 * @Description: TODO
 */
public class GetFormalParamTest {

    @Test
    void test(){
        String str = "{\"buildingArea\": \"${buildingArea}\",\n" +
                "            \"chargeArea\": \"${chargeArea}\",\n" +
                "            \"city\": \"${city}\",\n" +
                "            \"contractType\": \"${contractType}\",\n" +
                "            \"coveredArea\": \"${coveredArea}\",\n" +
                "            \"district\": \"${district}\",\n" +
                "            \"latitude\": \"${latitude}\",\n" +
                "            \"longitude\": ${longitude}\n" +
                "         }";
        ArrayList<String> arrayList = new ArrayList<>();
        CustomStrUtils.getFormalParam(str,arrayList);
        for (String str1: arrayList
             ) {
            System.out.println(str1);
        }
    }


}
