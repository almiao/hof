package com.lee.hof.controller;

import com.lee.hof.bean.model.Dish;
import com.lee.hof.dao.DishMapper;
import com.lee.hof.service.impl.DishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
