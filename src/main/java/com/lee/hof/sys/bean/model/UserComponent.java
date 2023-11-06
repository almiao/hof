package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@TableName("user_component")
@Entity
@Table(name="user_component")
public class UserComponent {

    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String verifyCode;

    private int validStatus;

    private int version;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
