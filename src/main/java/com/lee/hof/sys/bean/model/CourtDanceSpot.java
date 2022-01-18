package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.sql.Timestamp;



@Data
public class CourtDanceSpot extends BaseEntity{

    private Long id;
    /*
    跳舞点名称
     */
    private String name;

    /*
    舞点基本信息描述
     */
    private String detailDesc;

    /*
    logo图片
    */
    private String logoImgId;

    /*
    注意事项
     */
    private String attentionDesc;

    /*
        经度
     */
    private Double longitude;

    /*
    纬度
     */
    private Double latitude;

    private String createByUserName;

    private Long belongToSpot;

    private Long createBy;

    private Long updateBy;

    private String danceTypes;

    private Timestamp createTime;

    private Timestamp updateTime;


}
