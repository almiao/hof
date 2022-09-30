package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class VerifyComponentResponse {

    private String componentId;

    private List<String> fileId;


    private Map<String,String> extendInfo;


}
