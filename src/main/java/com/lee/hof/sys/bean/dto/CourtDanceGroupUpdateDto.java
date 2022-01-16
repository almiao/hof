package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Data
public class CourtDanceGroupUpdateDto extends BaseInput {

    @NotNull
    private Long id;

    //舞团名称 百望山舞团
    private String name;

    private String logoImgId;


    private Long courtDanceSpotId;

    //舞种描述 舞种类型
    private String danceTypes;

    //基本信息描述
    private String detailDesc;

    //注意事项
    private String attentionDesc;

}
