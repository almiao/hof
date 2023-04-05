package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.CompanionAddDto;
import com.lee.hof.sys.bean.dto.CompanionListDto;
import com.lee.hof.sys.bean.model.Companion;
import com.lee.hof.sys.service.CompanionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companion")
public class CompanionController {


    @Resource
    CompanionService companionService;

    @PostMapping("/add")
    public BaseResponse<Companion> addCompanion(@Valid @RequestBody CompanionAddDto dto){
        return BaseResponse.success(companionService.addCompanion(dto));
    }



    @PostMapping("/list")
    public BaseResponse<List<Companion>> listCompanion(@Valid @RequestBody CompanionListDto dto){
        return BaseResponse.success(companionService.listCompanion(dto));
    }


}
