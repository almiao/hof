package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.CommentDto;
import com.lee.hof.sys.bean.dto.CommentListDto;
import com.lee.hof.sys.bean.dto.CommentMineListDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.vo.CommentVo;
import com.lee.hof.sys.bean.vo.CommentMineVO;
import com.lee.hof.sys.mapper.CommentMapper;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.CommentService;
import com.lee.hof.sys.service.PostService;
import com.lee.hof.sys.service.UserService;
import com.lee.hof.sys.service.UserStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    @Resource
    UserStatisticService userStatisticService;


    @Override
    public CommentVo addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setParentCommentId(commentDto.getParentCommentId());
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        comment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        comment.setCommentTxt(commentDto.getCommentText());
        comment.setEntityId(commentDto.getEntityId());
        comment.setUserId(commentDto.getUserId());
        comment.setReplyToCommentId(commentDto.getReplyToCommentId());
        comment.setReplyToUserId(commentDto.getReplyToUserId());
        commentMapper.insert(comment);
        userStatisticService.addCommentCnt();
        return convert(comment);
    }

    @Override
    public List<CommentVo> listComment(CommentListDto dto) {
        QueryWrapper<Comment> conditions = new QueryWrapper<>();
        conditions.eq("entity_id", dto.getEntityId());
        if(Objects.nonNull(dto.getParentCommentId())) {
            conditions.eq("parent_comment_id",dto.getParentCommentId());
        }else {
            conditions.isNull("parent_comment_id");
        }
        conditions.eq(dto.getAuthorId() != null, "user_id", dto.getAuthorId());
        if(dto.getOrderBy() == 1){
            conditions.orderByDesc("id");
        }
        List<Comment> comments = commentMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions).getRecords();

        List<CommentVo> commentVos = new ArrayList<>();

        comments.forEach(comment -> {
            commentVos.add(convert(comment));
        });
        return commentVos;
    }

    @Resource
    PostMapper postMapper;

    @Override
    public List<CommentMineVO> listMine(CommentMineListDto dto) {
        Long me = UserContext.getUserId();
        List<Post> posts = postMapper.selectList(new QueryWrapper<Post>().eq("author_id",me));
        Set<Long> postId = posts.stream().map(Post::getId).collect(Collectors.toSet());
        QueryWrapper<Comment> conditions = new QueryWrapper<>();
        conditions.eq("reply_to_user_id", me);
        if(!postId.isEmpty()){
            conditions.or().in("entity_id", postId);
        }
        conditions.orderByDesc("id");
        List<Comment> comments = commentMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions).getRecords();
        List<CommentMineVO> commentVos = new ArrayList<>();
        comments.forEach(comment -> {
            commentVos.add(convertMine(comment));
        });
        return commentVos;
    }



    @Override
    public CommentVo convert(Comment comment) {
        if(comment == null){
            return null;
        }
        CommentVo commentVo = new CommentVo(comment);
        if(comment.getUserId()!=null) {
            commentVo.setUser(userService.getUserById(comment.getUserId()));
        }
        if(comment.getReplyToCommentId() != null){
            Comment reply = commentMapper.selectById(comment.getReplyToCommentId());
            CommentVo commentVo1 = new CommentVo(reply);
            commentVo1.setUser(userService.getUserById(reply.getUserId()));
            commentVo.setReplyTo(commentVo1);
        }
        int replyCnt = commentMapper.selectCount(new QueryWrapper<Comment>().eq("parent_comment_id", comment.getId()));
        List<Comment> childrens = commentMapper.selectList(new QueryWrapper<Comment>().eq("parent_comment_id", comment.getId()));
        List<CommentVo> childs =  childrens.stream().map(cm -> {
            CommentVo commentVo1 = new CommentVo(cm);
            commentVo1.setUser(userService.getUserById(cm.getUserId()));
            return commentVo1;
        }).collect(Collectors.toList());
        List<Like> likes = likeMapper.selectList(new QueryWrapper<Like>().eq("target_id", comment.getId().toString()).eq("target_entity_type","comment"));
        commentVo.setLikeList(likes);
        commentVo.setReplyList(childs);
        commentVo.setReplyCnt(replyCnt);
        return commentVo;
    }

    @Resource
    PostService postService;

    @Override
    public CommentMineVO convertMine(Comment comment) {
        if(comment == null){
            return null;
        }
        CommentMineVO commentVo = new CommentMineVO(comment);
        if(comment.getReplyToUserId()!=null) {
            commentVo.setReplyToUser(userService.getUserById(comment.getReplyToUserId()));
        }
        if(comment.getReplyToCommentId() != null){
            Comment reply = commentMapper.selectById(comment.getReplyToCommentId());
            commentVo.setReplyToComment(reply);
        }
        commentVo.setUser(userService.getUserById(comment.getUserId()));
        commentVo.setPost(postService.getSimplePost(Long.valueOf(comment.getEntityId())));
        return commentVo;
    }



    @Override
    public CommentMineVO getSimpleById(Long commentId) {
        return convertMine(commentMapper.selectById(commentId));
    }


}
