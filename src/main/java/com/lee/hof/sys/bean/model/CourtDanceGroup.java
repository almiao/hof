package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CourtDanceGroup extends BaseEntity {

    private Long id;

    private Long courtDanceSpotId;

    //舞团名称 百望山舞团
    private String name;

    private String logoImgId;

    //舞种描述 舞种类型
    private String danceTypes;

    //基本信息描述
    private String detailDesc;

    //注意事项
    private String attentionDesc;

    private Long createBy;

    private String createByUserName;

    private Long updateBy;

    private Timestamp createTime;

    private Timestamp updateTime;

}
