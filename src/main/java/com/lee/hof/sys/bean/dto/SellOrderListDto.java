package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
public class
SellOrderListDto extends BaseInput implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;

    private String searchTxt;

    private Double priceRangeLow;

    private Double priceRangeHigh;


    private Double distanceRangeLow;

    private Double distanceRangeHigh;

    private String orderByField;


    private int pageNum;

    private int pageSize;

}


