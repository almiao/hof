package com.lee.hof.common.constant;

import lombok.Data;

public enum  HofStatus {

    CONTINUE(1000, "Data logic Fail"),
    SWITCHING_PROTOCOLS(1001, "");
    private final int code;
    private final String msg;

    HofStatus(int code, String msg) {
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
