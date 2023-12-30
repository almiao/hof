package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

/**
 * 车友会
 */
@Data
@TableName("driver_group_user")
public class DriverGroupUser extends BaseEntity {

    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long driverGroupId;

    private Long userId;

    private String role;

    @TableField(exist =  false)
    private User user;

}
