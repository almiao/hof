package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.SellOrderListDto;
import com.lee.hof.sys.bean.model.SellOrder;
import com.lee.hof.sys.service.SellOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sellOrder")
public class OrderController extends BaseController {


    @Resource
    SellOrderService sellOrderService;


    @PostMapping("/new")
    public BaseResponse<Long> add() {
       return BaseResponse.success(sellOrderService.newSellOrder()) ;
    }


    @PostMapping("/save")
    public BaseResponse<SellOrder> save(@RequestBody SellOrder dto) {
        return BaseResponse.success(sellOrderService.saveSellOrder(dto));
    }

    @GetMapping("/validate")
    public BaseResponse<SellOrder> validate(@RequestParam String orderId) {
        return BaseResponse.success(sellOrderService.validate(orderId)) ;
    }


    @PostMapping("/get/detail")
    public BaseResponse<SellOrder> getDetail(@RequestParam Long orderId) {
        return BaseResponse.success(sellOrderService.getSellOrderDetail(orderId)) ;
    }




    @PostMapping("/get/editing")
    public BaseResponse<SellOrder> get() {
        return BaseResponse.success(sellOrderService.getEditingSellOrder()) ;
    }

    @PostMapping("/list/published")
    public BaseResponse<Page<SellOrder>> list(@RequestBody SellOrderListDto dto) {
        return BaseResponse.success(sellOrderService.listPublished(dto)) ;
    }

}

