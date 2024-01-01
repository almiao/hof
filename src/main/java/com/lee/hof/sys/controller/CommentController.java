package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.CommentDto;
import com.lee.hof.sys.bean.dto.CommentListDto;
import com.lee.hof.sys.bean.dto.CommentMineListDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.vo.CommentMineVO;
import com.lee.hof.sys.bean.vo.CommentVo;
import com.lee.hof.sys.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Resource
    CommentService commentService;

    @PostMapping("/add")
    public BaseResponse<CommentVo> addComment(@Valid @RequestBody CommentDto dto){
        return BaseResponse.success(commentService.addComment(dto));
    }

    @PostMapping("/list")
    public BaseResponse<List<CommentVo>> listComment(@RequestBody CommentListDto dto){
        return BaseResponse.success(commentService.listComment(dto));
    }

    @PostMapping("/listMine")
    public BaseResponse<List<CommentMineVO>> listMine(@RequestBody CommentMineListDto dto){
        return BaseResponse.success(commentService.listMine(dto));
    }

}
