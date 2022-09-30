package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.model.ShopDishDaily;
import com.lee.hof.sys.service.ShopDishDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ResponseBody
    @GetMapping("/view")
    public ResponseEntity<ShopDishDaily> viewById(@RequestParam Long id){
       ShopDishDaily dishDaily =
               shopDishDailyService.getById(id);

       return ResponseEntity.ok(dishDaily);
    }

}

