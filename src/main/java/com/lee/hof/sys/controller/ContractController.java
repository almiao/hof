package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.ContractUpdateDto;
import com.lee.hof.sys.bean.model.Contract;
import com.lee.hof.sys.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<Contract> createOrUpdate(@RequestBody ContractUpdateDto contract) {
        return BaseResponse.success(contractService.createOrUpdate(contract)) ;
    }


    @PostMapping("/list")
    public BaseResponse<List<Contract>> list() {
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("seller_user_id", UserContext.getUserId()).or().eq("buyer_user_id", UserContext.getUserId()).or().eq("create_by", UserContext.getUserId());
        return BaseResponse.success(contractService.list(queryWrapper)) ;
    }

}

