package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@TableName("entity_like")
@Table(name = "entity_like")
public class Like extends BaseEntity {

    @Id
    private Long id;

    @NotNull
    private String targetEntityType;

    @NotNull
    private Long targetId;

}
