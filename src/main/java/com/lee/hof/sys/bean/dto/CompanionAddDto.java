package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CompanionAddDto extends BaseInput {

    /**

     /**
     * 如4月下旬
     */
    private String title;

    /**
     * 作者描述
     */
    private String contentDesc;

    /**
     * 图片
     */
    private String picIds;

    /**
     * 出发城市
     */
    private String startCity;

    /**
     * 出发地点描述
     */
    private String startDetailLocationDesc;

    /**
     * 出发具体坐标
     */
    private String startDetailLocationPoi;


    /**
     * 到达城市
     */
    private String endCity;

    /**
     * 具体景点描述
     */
    private String endDetailLocationDesc;

    /**
     * 具体经典坐标
     */
    private String endDetailLocationPoi;

    /**
     * 活动类型
     */
    private String companionType;

    /**
     * 预期同行人数
     */
    private int expectPersonNum;

    /**
     * 预期开始日期
     */
    private String expectStartDate;

    /**
     * 预期结束日志
     */
    private String expectEndDate;

    /**
     *
     */
    private String referenceCompanionId;

    /**
     * 费用预期
     */
    private String maxCostDesc;


    /**
     * 费用
     */
    private String costShareType;

}
