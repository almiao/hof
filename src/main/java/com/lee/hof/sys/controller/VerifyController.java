package com.lee.hof.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.GuaziResponse;
import com.lee.hof.sys.bean.dto.VerifyComponentAddOrUpdateDto;
import com.lee.hof.sys.bean.dto.VerifyComponentGetDto;
import com.lee.hof.sys.bean.dto.VerifyItemListDto;
import com.lee.hof.sys.bean.model.VerifyComponentResponse;
import com.lee.hof.sys.bean.model.VerifyItem;
import com.lee.hof.sys.bean.vo.BrandResponse;
import com.lee.hof.sys.service.VerifyItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
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


    @GetMapping("/component/brand/list")
    public GuaziResponse<BrandResponse> listBrand() throws IOException {

        File respone = new File("src/main/resources/response.txt");
        String text = readTxt(respone);

        GuaziResponse<BrandResponse> responseBaseResponse = JSONObject.parseObject(text,new TypeReference<GuaziResponse<BrandResponse>>(){});

        return responseBaseResponse;
    }

    public static String readTxt(File file) throws IOException, IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s = br.readLine()) != null) {
            content = content.append(s);
        }
        return content.toString();
    }

}
