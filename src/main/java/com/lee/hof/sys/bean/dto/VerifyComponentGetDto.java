package com.lee.hof.sys.bean.dto;


import java.io.Serializable;


/**
 * Created by alex on 3/1/18.
 */

public class VerifyComponentGetDto implements Serializable {

    private String orderId;

    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

