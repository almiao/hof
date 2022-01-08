package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CourtDanceSpotAddDto extends BaseInput {



    /*
    跳舞点名称
     */
    private String danceSpotName;

    /*
    舞点基本信息描述
     */
    private String detailDesc;

    /*
    注意事项
     */
    private String attentionDesc;

    /*
    logo图片
    */
    private String logoImgId;

    private String danceTypes;



}
