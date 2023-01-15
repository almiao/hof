package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.dto.CommentDto;
import com.lee.hof.sys.bean.dto.CommentListDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.vo.CommentVo;
import com.lee.hof.sys.mapper.CommentMapper;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.service.CommentService;
import com.lee.hof.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Resource
    LikeMapper likeMapper;

    @Resource
    private UserService userService;


    @Override
    public CommentVo addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setParentCommentId(commentDto.getParentCommentId());
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        comment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        comment.setCommentTxt(commentDto.getCommentInfo());
        comment.setPostId(commentDto.getPostId());
        comment.setUserId(commentDto.getUserId());
        if( commentDto.getToUserId()!=null){
            comment.setToUserId(commentDto.getToUserId());
        }
        comment.setToCommentId(commentDto.getToCommentId());
        commentMapper.insert(comment);

        CommentVo commentVo = new CommentVo(comment);
        commentVo.setUser(userService.getUserById(comment.getUserId()));
        if(comment.getToUserId()!=null){
            commentVo.setToUser(userService.getUserById(comment.getToUserId()));
        }
        commentVo.setReplyCnt(0);
        commentVo.setReplyList(new ArrayList<>());
        return commentVo;
    }




    @Override
    public List<CommentVo> listComment(CommentListDto dto) {

        QueryWrapper<Comment> conditions = new QueryWrapper<>();

        conditions.eq("post_id", dto.getPostId()).orderByDesc("create_time");
        if(!StringUtils.isEmpty(dto.getParentCommentId())) {
            conditions.eq("parent_comment_id",dto.getParentCommentId());
        }else {
            conditions.isNull("parent_comment_id");
        }
        List<Comment> comments = commentMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions).getRecords();

        List<CommentVo> commentVos = new ArrayList<>();

        comments.forEach(comment -> {
            CommentVo commentVo = new CommentVo(comment);
            if(comment.getUserId()!=null) {
                commentVo.setUser(userService.getUserById(comment.getUserId()));
            }

            if(comment.getToUserId()!=null){
                commentVo.setToUser(userService.getUserById(comment.getToUserId()));
            }
            int replyCnt = commentMapper.selectCount(new QueryWrapper<Comment>().eq("parent_comment_id", comment.getId()));
            List<Comment> childrens = commentMapper.selectList(new QueryWrapper<Comment>().eq("parent_comment_id", comment.getId()).orderByAsc("create_time"));
            List<CommentVo> childs =  childrens.stream().map(cm -> {
                CommentVo commentVo1 = new CommentVo(cm);
                commentVo1.setUser(userService.getUserById(cm.getUserId()));
                if(cm.getToUserId()!=null){
                    commentVo1.setToUser(userService.getUserById(cm.getToUserId()));
                }
                return commentVo1;
            }).collect(Collectors.toList());

            List<Like> likes = likeMapper.selectList(new QueryWrapper<Like>().eq("target_id", comment.getId().toString()).eq("target_entity_type","comment"));
            commentVo.setLikeList(likes);
            commentVo.setReplyList(childs);
            commentVo.setReplyCnt(replyCnt);
            commentVos.add(commentVo);
        });
        return commentVos;
    }




}
