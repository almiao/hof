package com.lee.hof.sys.bean;

public enum UserFollowStatus {

    NORMAL(1, "正常"),
    DELETED(2, "删除"),
    ;


    private final int code;
    private final String msg;

    UserFollowStatus(int code, String msg) {
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
