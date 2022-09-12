package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@TableName("entity_like")
@Table(name = "entity_like")
public class Like extends BaseEntity {

    @Id
    private String id;

    private String targetEntityType;

    private String targetId;

    private int level;

    private int isDel;

}
