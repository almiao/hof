package com.lee.hof.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.GuaziResponse;
import com.lee.hof.sys.bean.dto.VerifyComponentAddOrUpdateDto;
import com.lee.hof.sys.bean.dto.VerifyComponentGetDto;
import com.lee.hof.sys.bean.dto.VerifyItemListDto;
import com.lee.hof.sys.bean.model.VerifyComponent;
import com.lee.hof.sys.bean.model.VerifyItem;
import com.lee.hof.sys.bean.vo.BrandResponse;
import com.lee.hof.sys.service.VerifyItemService;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
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

    @PostMapping("/component/getLatest")
    public BaseResponse<VerifyComponent> getVerifyComponent(@RequestBody VerifyComponentGetDto dto) {
        return BaseResponse.success(verifyItemService.getVerifyComponent(dto));
    }


    @GetMapping("/component/brand/list")
    public GuaziResponse<BrandResponse> listBrand() throws IOException {
        ClassPathResource resource = new ClassPathResource("response.txt");
        InputStream inputStream = resource.getInputStream();
        File tempFile = File.createTempFile("temp", ".txt");
        FileUtils.copyInputStreamToFile(inputStream, tempFile);

        String text = FileUtils.readFileToString(tempFile, StandardCharsets.UTF_8);

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
