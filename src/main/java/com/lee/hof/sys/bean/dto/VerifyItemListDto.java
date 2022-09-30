package com.lee.hof.sys.bean.dto;


import java.io.Serializable;


/**
 * Created by alex on 3/1/18.
 */

public class VerifyItemListDto implements Serializable {

    private String userId;

    private String orderId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

