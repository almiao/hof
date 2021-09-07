package com.lee.hof.controller;

import com.lee.hof.dao.DishMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangle
 * @description 管理饭店相关接口
 * @date 2021/9/3
 */
@RestController
@RequestMapping("/shop")
public class DishDaliyController {


    @PostMapping("/view")
    public void viewShop(@Param("id") Long id){

        System.out.println("abcdefdddddddddddddddddddddddddd");


    }


}
