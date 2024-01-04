package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@TableName("entity_collect")
@Table(name = "entity_collect")
public class Collect extends BaseEntity {

    @Id
    private Long id;

    private String targetEntityType;

    private Long targetId;


}
