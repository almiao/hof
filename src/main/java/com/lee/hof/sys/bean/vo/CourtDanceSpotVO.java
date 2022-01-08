package com.lee.hof.sys.bean.vo;


import com.lee.hof.sys.bean.model.BaseOutput;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class CourtDanceSpotVO extends BaseOutput {
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

    private String createByUserName;

    private String danceTypes;

    private Timestamp createTime;

    private Timestamp updateTime;




}
