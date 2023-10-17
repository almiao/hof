package com.lee.hof.sys.bean.enums;

public enum VerifyCodeEnum {
    D001(1, "D001","身份证"),
    D002(2, "D002","行驶证"),
    D003(3, "D003","驾驶证"),
    D004(4, "D004","购车合同"),
    D005(5, "D005","人脸认证"),
    D006(6, "D006","我的车辆"),
    D007(7, "D007","心愿车辆"),
    ;

    private final int code;

    private final String verifyCode;

    private String desc;


    VerifyCodeEnum(int code, String msg,String dec) {
        this.code = code;
        this.verifyCode = msg;
        this.desc = dec;
    }

    public int getCode() {
        return code;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public static VerifyCodeEnum getByCode(int code){

        for(VerifyCodeEnum statusEnum: VerifyCodeEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
