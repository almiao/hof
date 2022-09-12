package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.CommentDto;
import com.lee.hof.sys.bean.dto.CommentListDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface CommentService extends IService<Comment> {

    String addComment(CommentDto comment);



    List<CommentVo> listComment(CommentListDto dto);
    
}
