package com.lee.hof.sys.bean.enums;

public enum VerifyStatusEum {

    init(0, "默认"),
    NEED_VERIFY(1, "待验证"),
    Success(2, "验证成功"),
    FAIL(3, "验证失败");

    private final int code;

    private final String msg;

    VerifyStatusEum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static VerifyStatusEum getByCode(int code){

        for(VerifyStatusEum statusEnum:VerifyStatusEum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
