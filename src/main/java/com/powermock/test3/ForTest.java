package com.powermock.test3;

public class ForTest {
    public String executeFunction(){
        FirstUtil.staticFunction("FirstUtil");
        SecondUtil.staticFunction("SecondUtil");
        return "success";
    }
}
