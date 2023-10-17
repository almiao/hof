package com.lee.hof.sys.bean.enums;

public enum ValidStatusEum {

    init(0, "默认"),
    VALID(1, "有效"),
    UNVALID(2, "无效"),
    FAIL(3, "验证失败");

    private final int code;

    private final String msg;

    ValidStatusEum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ValidStatusEum getByCode(int code){

        for(ValidStatusEum statusEnum: ValidStatusEum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
