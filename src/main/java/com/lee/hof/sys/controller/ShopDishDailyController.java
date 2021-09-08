package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.model.ShopDishDaily;
import com.lee.hof.sys.service.ShopDishDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.lee.hof.sys.controller.BaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangle
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/shopDishDaily")
public class ShopDishDailyController extends BaseController {

    @Autowired
    ShopDishDailyService shopDishDailyService;



    @GetMapping("/view")
    public ShopDishDaily viewById(@RequestParam Long id){
       return shopDishDailyService.getById(id);
    }

    public

}

