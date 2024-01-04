package com.lee.hof.sys.bean.enums;

import java.util.Objects;

public enum EntityTypeEnum {

    POST(0, "post"),
    COMMENT(1, "comment");

    private final int code;

    private final String name;

    EntityTypeEnum(int code, String msg) {
        this.code = code;
        this.name = msg;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static EntityTypeEnum getByCode(int code){
        for(EntityTypeEnum statusEnum: EntityTypeEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }
        return null;
    }

    public static EntityTypeEnum getByName(String msg){
        for(EntityTypeEnum statusEnum: EntityTypeEnum.values()){
            if(Objects.equals(statusEnum.getName(), msg)){
                return statusEnum;
            }
        }
        return null;
    }
}
