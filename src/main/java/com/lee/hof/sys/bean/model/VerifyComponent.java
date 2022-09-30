package com.lee.hof.sys.bean.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@Data
@TableName("verify_component")
@Entity
@Table(name="verify_component")
public class VerifyComponent {

    @Id
    private String id;

    private long userId;

    private String verifyCode;

    private String fileIds;

    private String orderId;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;


    private String extendInfo;


    public Map<String,String> getExtendMap(){
        return JSON.parseObject(extendInfo, new TypeReference<HashMap<String,String>>(){});
    }


}
