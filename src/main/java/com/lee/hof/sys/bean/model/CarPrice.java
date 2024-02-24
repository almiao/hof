package com.lee.hof.sys.bean.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class CarPrice implements Serializable {

    private Long id;


    private String purchaseTime;

    /**
     * 落地价
     */
    private String totalPrice;

    /**
     * 裸车价
     */
    private String beforeTaxPrice;

    private String location;

    private String billFillId;


    private Date createTime;

    private Date updateTime;

}
