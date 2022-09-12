package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UndoLike implements Serializable {


    private String targetEntityType;

    private String targetId;

    private Long createBy;

    private int level;

}
