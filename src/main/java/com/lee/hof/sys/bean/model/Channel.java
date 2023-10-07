package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@TableName("channel")
@Table(name = "channel")
public class Channel extends BaseEntity {


    @Id
    private int id;

    private String name;

    private String coverFileId;

}
