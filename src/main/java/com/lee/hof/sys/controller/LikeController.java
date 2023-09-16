package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/like")
public class LikeController {


    @Resource
    LikeService likeService;

    @PostMapping("/add")
    public BaseResponse<String> addLike(@RequestBody Like like){
        return BaseResponse.success(likeService.add(like));
    }

    @PostMapping("/del")
    public BaseResponse<String> delLike(@RequestBody UndoLike like){
        return BaseResponse.success(likeService.undoLike(like));
    }



}
