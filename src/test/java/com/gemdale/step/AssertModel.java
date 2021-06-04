package com.gemdale.step;

import com.gemdale.utils.CustomStrUtils;

/**
 * ClassName: AssertModel
 * date: 2020/12/30 20:29
 * @author JJJJ
 * Description: assert 映射对象
 */
public class AssertModel {
    /*
    asserts:
      - actual: errcode
        matcher: equalTo
        expect: 2
        reason: 'getToken错误码校验！'
     */
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    /**
     * 进行数据复制
     * @param oldModel 原有数据储存对象
     * @param targetModel 目标储存数据对象
     */
    public static void copyProperties(AssertModel oldModel,AssertModel targetModel){
        String actual = oldModel.getActual();
        String expect = oldModel.getExpect();
        String matcher = oldModel.getMatcher();
        String reason = oldModel.getReason();
        if (!CustomStrUtils.isEmpty(actual))
            targetModel.setActual(actual);
        if (!CustomStrUtils.isEmpty(expect))
            targetModel.setExpect(expect);
        if (!CustomStrUtils.isEmpty(matcher))
            targetModel.setMatcher(matcher);
        if (!CustomStrUtils.isEmpty(reason))
            targetModel.setReason(reason);
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
