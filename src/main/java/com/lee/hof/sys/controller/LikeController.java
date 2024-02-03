package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.LikeListDto;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.vo.LikeVO;
import com.lee.hof.sys.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {


    @Resource
    LikeService likeService;

    @PostMapping("/add")
    public BaseResponse<Long> addLike(@RequestBody @Valid Like like){
        return BaseResponse.success(likeService.add(like));
    }

    @PostMapping("/del")
    public BaseResponse<Long> delLike(@RequestBody UndoLike like){
        return BaseResponse.success(likeService.undoLike(like));
    }

    @PostMapping("/listLikeMe")
    public BaseResponse<List<LikeVO>> listLikeMe(){
        return BaseResponse.success(likeService.listLikeMe());
    }
    @PostMapping("/listMyLike")
    public BaseResponse<List<LikeVO>> listMyLike(@RequestBody LikeListDto like){
        return BaseResponse.success(likeService.listMyLike(like));
    }

}
