package com.lee.hof.sys.bean.model;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {

    POST(1,"post"),
    COMMENT(2,"comment");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    EntityType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    private static final Map<String, EntityType> CACHE = new HashMap<String, EntityType>();

    static {
        for (EntityType val : EntityType.values()) {
            CACHE.put(val.getName(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static EntityType parse(String c) {
        return CACHE.get(c);
    }

}
