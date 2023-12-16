package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
public class SellOrder implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer status;

    @Id
    private Long id;

    private Long userId;


    private String baseInfoText;


    private String coverFileId;


    private String fileIds;


    private String positionTxt;


    private String positionLongitude;


    private String positionLatitude;


    private String licenseNumInput;


    private String brandName;


    private String brandSeries;


    private String brandDetailName;


    private String licenseTime;

    /**
     * 行驶里程
     */

    private Double distance;


    private Double price;


    private Double originPrice;


    private boolean needInspectionService;

    private boolean needCollideInfoSearch;

    private int validStatus;

    @TableField(exist = false)
    private User user;

    private int coverWidth;

    private int coverHeight;

}


