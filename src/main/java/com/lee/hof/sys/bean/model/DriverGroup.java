package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

/**
 * 车友会
 */
@Data
@TableName("driver_group")
public class DriverGroup extends BaseEntity {

    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String coverFileId;

    private String iconFileId;

    private Long chairManUserId;

    /**
     * 车友会描述
     */
    private String groupDesc;

    /**
     * 服务地区
     */
    private String location;



    @TableField(exist = false)
    private List<DriverGroupUser> driverGroupUsers;


}
