package com.lee.hof.sys.bean.model;


import lombok.Data;

/**
 * 行驶证认证内容
 */
@Data
public class VerifyDrivingLicenseContent {

    private String frontPicId;

    private String backPicId;

    private String name;

    private String cardCode;

}
