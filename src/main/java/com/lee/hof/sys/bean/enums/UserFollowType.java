package com.lee.hof.sys.bean.enums;

public enum UserFollowType {

    USER(0, "用户"),
    ;


    private final int code;
    private final String msg;

    UserFollowType(int code, String msg) {
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
