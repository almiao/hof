package com.lee.hof.sys.bean.enums;

public enum CommonStatusEum {

    INIT(0, "默认-有效"),
    DELETE(1, "已删除"),
    HIDE(2, "隐藏");

    private final int code;

    private final String msg;

    CommonStatusEum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CommonStatusEum getByCode(int code){

        for(CommonStatusEum statusEnum: CommonStatusEum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
