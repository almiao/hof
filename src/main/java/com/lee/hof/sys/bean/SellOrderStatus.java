package com.lee.hof.sys.bean;

public enum SellOrderStatus {

    EDITING(1, "编辑中"),
    VALIDATING(2, "审核中"),
    PUBLISHED(3, "已发布"),
    PAID(4, "已付款"),
    SALED(5, "售出");


    private final int code;
    private final String msg;

    SellOrderStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
