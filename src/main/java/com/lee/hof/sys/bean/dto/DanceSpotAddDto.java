package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.BaseEntity;
import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class DanceSpotAddDto extends BaseInput {


    private String desc;

    private String pos;

    private String logoImgId;

    private String danceSpotName;



}
