package com.lee.hof.sys.bean.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
public class CompanionListDto implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;

    private String startCity;

    private int pageNum;

    private int pageSize;

}


