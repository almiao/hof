package com.lee.hof.controller;


import com.lee.hof.bean.vo.MenuVO;
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
            menuVO.setDishes(Arrays.asList("红烧鸡翅","回锅肉","面包"));
        }

        return menuVO;
    }





}
