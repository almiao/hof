package com.lee.hof.sys.controller;

import com.lee.hof.sys.bean.model.Dish;
import com.lee.hof.sys.service.impl.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import org.apache.ibatis.annotations.Param;

/**
 * @author tangle
 * @description 管理饭店相关接口
 * @date 2021/9/3
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/view")
    public Dish viewShop(@RequestParam("id") Long id){
        return dishService.getDishById(id);
    }


}
