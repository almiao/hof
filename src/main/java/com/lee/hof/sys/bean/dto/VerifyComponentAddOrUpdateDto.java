package com.lee.hof.sys.bean.dto;


import java.io.Serializable;
import java.util.Map;


/**
 * Created by alex on 3/1/18.
 */

public class VerifyComponentAddOrUpdateDto implements Serializable {

    private String componentId;

    private String verifyCode;

    private String fileIds;

    private Map<String, String> extendInfo;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, String> extendInfo) {
        this.extendInfo = extendInfo;
    }
}

