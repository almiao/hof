package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

//用户活动表
@Entity
@Data
@TableName("user_statistic")
@Table(name = "user_statistic")
public class UserStatistic{

    @Id
    private Long id;

    private Long userId;

    private long likeReadId;

    private long collectReadId;

    private long commentReadId;

    private int postCnt;

    private int commentCnt;

    private int likeCnt;

    private int collectCnt;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

}
