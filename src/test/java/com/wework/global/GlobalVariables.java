package com.wework.global;

import java.util.HashMap;

/**
 * ClassName: GlobalVariables
 * date: 2020/12/30 8:58
 *
 * @author JJJJ
 * Description:
 */
public class GlobalVariables {

    static HashMap<String,String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
