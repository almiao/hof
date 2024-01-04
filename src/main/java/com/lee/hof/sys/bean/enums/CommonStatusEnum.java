package com.lee.hof.sys.bean.enums;

public enum CommonStatusEnum {

    INIT(0, "默认-有效"),
    DELETE(1, "已删除"),
    HIDE(2, "隐藏");

    private final int code;

    private final String msg;

    CommonStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CommonStatusEnum getByCode(int code){

        for(CommonStatusEnum statusEnum: CommonStatusEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
