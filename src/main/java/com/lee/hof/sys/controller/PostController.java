package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostDelDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.PostDetailVo;
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
    public ResponseEntity<String> addPost(@RequestBody  PostAddDto dto){
        return ResponseEntity.ok(postService.addPost(dto));
    }

    @PostMapping("/del")
    public ResponseEntity<Boolean> delPost(@RequestBody  PostDelDto delDto){
        return ResponseEntity.ok(postService.delPost(delDto.getUser(), delDto.getPostId()));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Post>> listPost(@RequestBody PostListDto dto){
        return ResponseEntity.ok(postService.listPost(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updatePost(@RequestBody PostUpdateDto dto){
        return ResponseEntity.ok(postService.updatePost(dto));
    }

    @PostMapping("/detail/{postId}")
    public ResponseEntity<PostDetailVo> getPostDetail(@PathVariable(value = "postId") String postId){
        return ResponseEntity.ok(postService.getDetail(new User(),postId));
    }

}
