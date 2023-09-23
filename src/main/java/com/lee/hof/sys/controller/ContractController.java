package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.Contract;
import com.lee.hof.sys.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {

    @Autowired
    ContractService contractService;


    @PostMapping("/get")
    public BaseResponse<Contract> get(@RequestParam Long contractId) {
       return BaseResponse.success(contractService.getById(contractId)) ;
    }

    @PostMapping("/createOrUpdate")
    public BaseResponse<Contract> createOrUpdate(@RequestParam Contract contractId) {
        return BaseResponse.success(contractService.createOrUpdate(contractId)) ;
    }

    @PostMapping("/list")
    public BaseResponse<List<Contract>> list() {
        return BaseResponse.success(contractService.list()) ;
    }




}

