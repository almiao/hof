package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseCourtDanceSpot {

    private Integer id;

    private String desc;

    private String createByUserName;

    private Timestamp createTime;

    private String danceTypes;

    private String mainImgId;



}
