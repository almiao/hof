package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostDelDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.vo.PostVO;
import com.lee.hof.sys.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/post")
public class PostController {


    @Resource
    PostService postService;

    @PostMapping("/add")
    public BaseResponse<PostVO> addPost(@RequestBody PostAddDto dto){
        return BaseResponse.success(postService.addPost(dto));
    }

    @PostMapping("/del")
    public ResponseEntity<Boolean> delPost(@RequestBody  PostDelDto delDto){
        return ResponseEntity.ok(postService.delPost(delDto.getUser(), delDto.getPostId()));
    }

//    @PostMapping("/search")
//    public ResponseEntity<Page<Post>> searchPost(@RequestBody PostListDto dto){
//        return ResponseEntity.ok(postService.searchPost(dto));
//    }

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
