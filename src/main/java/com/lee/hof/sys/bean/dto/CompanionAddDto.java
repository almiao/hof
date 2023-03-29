package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class CompanionAddDto extends BaseInput {

    /**
     * 标题
     */
    @NotBlank(message = "title should not be null")
    private String title;

    /**
     * 描述
     */
    @NotBlank(message = "desc should not be null")
    private String contentDesc;

    /**
     * 相关图片
     */
    private String picIds;

    /**
     * 出发城市
     */
    private String startCity;

    /**
     * 出发地点
     */
//    @NotBlank(message = "startDetailLocation should not be null")
    private String startDetailLocation;

    /**
     * 到达城市
     */
//    @NotBlank(message = "endCity should not be null")
    private String endCity;

    /**
     * 约伴类型
     */
    private String companionType;

    /**
     * 期望人数
     */
    private int expectPersonNum;

    /**
     * 开始时间
     */
    private String expectStartDate;

    /**
     * 结束时间
     */
    private String expectEndDate;

    /**
     * 相关旅行游记
     */
    private String referenceCompanionId;

}
