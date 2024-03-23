package com.lee.hof.sys.bean.enums;

public enum UserFollowStatus {

    NORMAL(1, "正常"),
    DELETED(2, "删除"),

    HIDE(3, "屏蔽"),
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
