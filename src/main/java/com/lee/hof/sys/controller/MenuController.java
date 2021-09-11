package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.vo.DishSimpleVO;
import com.lee.hof.sys.bean.vo.MenuVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @GetMapping("/list/my")
    public MenuVO getMyMenu(@RequestParam Integer userId){

        MenuVO menuVO = new MenuVO();

        if(userId == 1){

            DishSimpleVO vo1 = new DishSimpleVO(1,1,"红烧鸡块");
            DishSimpleVO vo2 = new DishSimpleVO(2,3,"回锅肉");
            DishSimpleVO vo3 = new DishSimpleVO(3,3,"面包");
            menuVO.setDishes(Arrays.asList(vo1,vo2,vo3));
        }

        return menuVO;
    }





}