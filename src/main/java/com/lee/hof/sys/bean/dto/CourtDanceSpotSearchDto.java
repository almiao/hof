package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CourtDanceSpotSearchDto extends BaseInput {

    /*
    跳舞点名称
     */
    private String name;


    private String danceType;

    /*
    经度
    */
    private Double longitude;

    /*
    纬度
     */
    private Double latitude;

    /*
    纬度
     */
    private Double radius;


    private int pageNo;

    private int pageSize = 20;

}
