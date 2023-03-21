package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
@TableName("companion")
@Entity
@Table(name="companion")
public class Companion implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;



    @Id
    private Long id;


    private String title;

    private String desc;

    private String picIds;

    private String startCity;

    private String startDetailLocation;

    private String endCity;

    private String companionType;

    private int expectPersonNum;

    private String expectStartDate;

    private String expectEndDate;

    private String referenceCompanionId;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer status;
}


