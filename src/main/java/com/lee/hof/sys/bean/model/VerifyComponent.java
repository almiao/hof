package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@TableName("verify_component")
@Entity
@Table(name="verify_component")
public class VerifyComponent {

    @Id
    private String id;

    private long userId;

    private String verifyCode;

    private String verifyContent;

    private String verifyType;

    private int verifyStatus;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
