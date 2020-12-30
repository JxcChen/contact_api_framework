package com.wework.step;

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
