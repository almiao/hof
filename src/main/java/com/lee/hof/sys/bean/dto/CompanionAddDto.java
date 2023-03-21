package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CompanionAddDto extends BaseInput {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String desc;

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
    private String startDetailLocation;

    /**
     * 到达城市
     */
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
