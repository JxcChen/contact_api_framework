package com.contact.test;

import com.contact.utils.CustomStrUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Test
    void test2(){
        HashMap<String,String> map = new HashMap<>();
        /*
        /*
          contractType: ${contractType}
          current: ${current}
          orgCode: ${orgCode}
          proName: ${proName}
          proType: ${proType}
          status: ${status}
          size: ${size}
         */

        map.put("contractType","${contractType}");
        map.put("current","${current}");
        map.put("orgCode","${orgCode}");
        map.put("proName","${proName}");
        ArrayList<String> arrayList = new ArrayList<>();
        CustomStrUtils.getFormalParam(map,arrayList);
        for (String str1: arrayList
        ) {
            System.out.println(str1);
        }
    }
}
