package com.lee.hof.sys.bean.enums;

public enum PostActionTypeEnum {

    init(0, "默认"),
    LIKE(1, "点赞"),
    COLLECT(2, "收藏"),
    FORWARD(3, "转发"),

    REPORT(4, "举报");

    private final int code;

    private final String msg;

    PostActionTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static PostActionTypeEnum getByCode(int code){
        for(PostActionTypeEnum statusEnum: PostActionTypeEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }
        return null;
    }


}
