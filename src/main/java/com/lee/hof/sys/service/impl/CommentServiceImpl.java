package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.dto.CommentDto;
import com.lee.hof.sys.bean.dto.CommentListDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.vo.CommentVo;
import com.lee.hof.sys.mapper.CommentMapper;
import com.lee.hof.sys.mapper.UserMapper;
import com.lee.hof.sys.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    CommentMapper commentMapper;


    @Override
    public String addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        comment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        comment.setCommentInfo(commentDto.getCommentInfo());
        comment.setPostId(commentDto.getPostId());
        comment.setUserId(commentDto.getUserId());
        comment.setToCommentId(commentDto.getToCommentId());
        commentMapper.insert(comment);
        return comment.getId();
    }

    @Resource
    UserMapper userMapper;


    @Override
    public List<CommentVo> listComment(CommentListDto dto) {

        QueryWrapper<Comment> conditions = new QueryWrapper<>();
        conditions.eq("post_id", dto.getPostId()).orderByDesc("remark");

        if(StringUtils.isEmpty(dto.getCommentId())) {
            conditions.isNull("to_comment_id");
        }else{
            conditions.eq("to_comment_id",dto.getCommentId());
        }
        List<Comment> comments = commentMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions).getRecords();

        return comments.stream().map(comment -> {
            CommentVo commentVo = new CommentVo(comment);
            commentVo.setUser(userMapper.selectById(comment.getUserId() == null?"1":comment.getUserId()));
            int replyCnt = commentMapper.selectCount(new QueryWrapper<Comment>().eq("to_comment_id", comment.getId()));
            commentVo.setReplyCnt(replyCnt);
            return commentVo;
        }).collect(Collectors.toList());
    }




}
