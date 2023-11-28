package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.*;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.UserPostAction;
import com.lee.hof.sys.bean.vo.PostVO;
import com.lee.hof.sys.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {


    @Resource
    PostService postService;

    @PostMapping("/add")
    public BaseResponse<PostVO> addPost(@RequestBody PostAddDto dto){
        return BaseResponse.success(postService.addPost(dto));
    }

    @PostMapping("/vote")
    public BaseResponse<UserPostAction> addPostVote(@RequestBody PostVoteDto dto){
        return BaseResponse.success(postService.addOrUpdatePostAction(dto));
    }


    @PostMapping("/del")
    public ResponseEntity<Boolean> delPost(@RequestBody  PostDelDto delDto){
        return ResponseEntity.ok(postService.delPost(delDto.getUser(), delDto.getPostId()));
    }

    @PostMapping("/search")
    public BaseResponse<IPage<Post>> searchPost(@RequestBody PostSearchDto dto){
        return BaseResponse.success(postService.searchPost(dto));
    }

    @PostMapping("/search/hint")
    public BaseResponse<List<String>> searchPostHint(@RequestBody PostSearchDto dto){
        return BaseResponse.success(postService.searchPostHint(dto));
    }

    @PostMapping("/search/history")
    public BaseResponse<List<String>> listSearchHistory(){
        return BaseResponse.success(postService.listSearchHistory());
    }

    @PostMapping("/search/history/del")
    public BaseResponse<Boolean> delSearchHistory(){
        return BaseResponse.success(postService.delSearchHistory());
    }

    @PostMapping("/list")
    public BaseResponse<IPage<PostVO>> listPost(@RequestBody PostListDto dto){
        return BaseResponse.success(postService.listPost(dto));
    }


    @PostMapping("/update")
    public ResponseEntity<Boolean> updatePost(@RequestBody PostUpdateDto dto){
        return ResponseEntity.ok(postService.updatePost(dto));
    }

    @PostMapping("/detail")
    public BaseResponse<PostVO> getPostDetail(@RequestParam(value = "id") String id){
        return BaseResponse.success(postService.getDetail(id));
    }

}
