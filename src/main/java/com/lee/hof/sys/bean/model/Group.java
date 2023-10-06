package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_group")
public class Group extends BaseEntity {

    private String name;

    private String coverFileId;

}
