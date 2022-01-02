package com.lee.hof.sys.controller;

import com.lee.hof.sys.bean.model.Dish;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */


@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(){
        return "hello boy!";
    }
}
