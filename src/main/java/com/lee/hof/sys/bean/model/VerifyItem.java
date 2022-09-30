package com.lee.hof.sys.bean.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@TableName("verify_item")
@Entity
@Table(name="verify_item")
public class VerifyItem implements Serializable {

    @Id
    private String id;

    private String iconId;

    private String title;

    private int verifyStatus;

    private String verifyCode;

}
