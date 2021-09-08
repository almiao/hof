package com.lee.hof.sys.bean.vo;


import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class DishDaliyVO {

    private Date date;

    private ShopVO shop;

    private DishVO dish;

    public List<StepVO> steps;


}
