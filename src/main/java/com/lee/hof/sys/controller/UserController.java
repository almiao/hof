package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.service.UserComponentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserComponentService postService;

    @PostMapping("/component/add")
    public BaseResponse<String> addComponent(@RequestParam("file") MultipartFile file,
                                             @RequestParam("componentId") String componentId) throws IOException {
        return BaseResponse.success(postService.addComponent(file,componentId));
    }




}
