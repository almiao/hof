package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CourtDanceGroupSearchDto extends BaseInput {

    /*
    跳舞点名称
     */
    private Long courtDanceSpotId;

    /*
    舞点基本信息描述
     */
    private String detailDesc;


    private String danceType;

    private String name;


    private int pageNum = 0;

    private int pageSize = 99;

    private String sortBy;

}
