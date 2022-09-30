package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.VerifyComponentAddOrUpdateDto;
import com.lee.hof.sys.bean.dto.VerifyComponentGetDto;
import com.lee.hof.sys.bean.dto.VerifyItemListDto;
import com.lee.hof.sys.bean.model.VerifyComponentResponse;
import com.lee.hof.sys.bean.model.VerifyItem;
import com.lee.hof.sys.service.VerifyItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/verify")
public class VerifyController {

    @Resource
    VerifyItemService verifyItemService;

    @PostMapping("/list")
    public BaseResponse<List<VerifyItem>> listVerifyItem(@RequestBody VerifyItemListDto dto) {
        return BaseResponse.success(verifyItemService.listVerifyItem(dto));
    }

    @PostMapping("/component/add")
    public BaseResponse<String> addVerifyComponent(@RequestBody VerifyComponentAddOrUpdateDto dto) {
        return BaseResponse.success(verifyItemService.updateVerifyComponent(dto));
    }

    @PostMapping("/component/get")
    public BaseResponse<VerifyComponentResponse> getVerifyComponent(@RequestBody VerifyComponentGetDto dto) {
        return BaseResponse.success(verifyItemService.getVerifyComponent(dto));
    }

}
