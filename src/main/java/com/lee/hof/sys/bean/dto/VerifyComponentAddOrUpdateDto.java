package com.lee.hof.sys.bean.dto;


import java.io.Serializable;


/**
 * Created by alex on 3/1/18.
 */

public class VerifyComponentAddOrUpdateDto implements Serializable {

    private String verifyComponentId;

    private String verifyCode;

    private String verifyContent;


    public String getVerifyComponentId() {
        return verifyComponentId;
    }

    public void setVerifyComponentId(String verifyComponentId) {
        this.verifyComponentId = verifyComponentId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyContent() {
        return verifyContent;
    }

    public void setVerifyContent(String verifyContent) {
        this.verifyContent = verifyContent;
    }

}

