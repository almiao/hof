package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.LikeListDto;
import com.lee.hof.sys.bean.enums.CommonStatusEnum;
import com.lee.hof.sys.bean.enums.EntityTypeEnum;
import com.lee.hof.sys.bean.model.*;
import com.lee.hof.sys.bean.vo.CommentVo;
import com.lee.hof.sys.bean.vo.LikeVO;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Resource
    LikeMapper likMapper;
    @Resource
    PostMapper postMapper;

    @Resource
    UserStatisticService userStatisticService;

    @Override
    public Long add(Like like) {
        Like db = likMapper.selectOne(new QueryWrapper<Like>().eq("create_by", UserContext.getUserId()).eq("target_id", like.getTargetId())
                .eq("status",0).orderByAsc().last("limit 1"));
        if(db != null){
            return db.getId();
        }
        like.setCreateBy(UserContext.getUserId());
        like.setCreateTime(new Timestamp(System.currentTimeMillis()));
        like.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        like.setStatus(CommonStatusEnum.INIT.getCode());
        like.setTargetEntityType(like.getTargetEntityType());
        likMapper.insert(like);
        userStatisticService.addLikeCnt();
        userStatisticService.addLikedCnt(EntityType.parse(like.getTargetEntityType()), like.getTargetId());
        return like.getId();
    }

    @Override
    public String cnt() {
        return null;
    }

    @Override
    public Long undoLike(UndoLike id) {
        Like like = likMapper.selectOne(new QueryWrapper<Like>().eq("create_by", UserContext.getUserId()).eq("target_id", id.getTargetId())
        .eq("status",0).orderByAsc().last("limit 1"));
        if(like == null){
            return null;
        }
        like.setStatus(CommonStatusEnum.DELETE.getCode());
        likMapper.updateById(like);
        return like.getId();
    }

    @Override
    public List<LikeVO> listLikeMe() {
        List<Post> myPosts = postMapper.selectList(new QueryWrapper<Post>().eq("author_id", UserContext.getUserId()));
        Set<Long> postId = myPosts.stream().map(Post::getId).collect(Collectors.toSet());

        List<Comment> myComments = commentService.list(new QueryWrapper<Comment>().eq("create_by", UserContext.getUserId()));
        Set<Long> commentId = myComments.stream().map(Comment::getId).collect(Collectors.toSet());

        if(postId.isEmpty() && commentId.isEmpty()){
            return new ArrayList<>();
        }

        QueryWrapper<Like> queryWrapper =  new QueryWrapper<Like>().eq("status",0);

        if(!postId.isEmpty() && commentId.isEmpty()){
            queryWrapper.eq("target_entity_type","post").in("target_id",postId);
        }else if(!commentId.isEmpty() && postId.isEmpty()){
            queryWrapper.eq("target_entity_type","comment").in("target_id",commentId);
        }else{
            queryWrapper.and(likeQueryWrapper2 -> likeQueryWrapper2.and(likeQueryWrapper -> likeQueryWrapper.eq("target_entity_type","post").in("target_id",postId))
                    .or(likeQueryWrapper1 -> likeQueryWrapper1.eq("target_entity_type","comment").in("target_id",commentId)));
        }

        queryWrapper.orderByDesc("id");

        List<Like> likes = likMapper.selectList(queryWrapper);

        return likes.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<LikeVO> listMyLike(LikeListDto like) {
        List<Like> likes = likMapper.selectList(new QueryWrapper<Like>().eq("create_by", like.getCreateBy())
                .eq("status", 0).orderByDesc());

        return likes.stream().map(this::convert).collect(Collectors.toList());
    }

    @Resource
    PostService postService;

    @Resource
    CommentService commentService;


    @Resource
    UserService userService;

    private LikeVO convert(Like like){
        if(like == null){
            return null;
        }
        LikeVO likeVO = new LikeVO();
        BeanUtils.copyProperties(like, likeVO);
        likeVO.setUser(userService.getUserById(like.getCreateBy()));

        if(Objects.equals(like.getTargetEntityType(), EntityTypeEnum.POST.getName())){
            likeVO.setPost(postService.getSimplePost(like.getTargetId()));
        }else if(Objects.equals(like.getTargetEntityType(), EntityTypeEnum.COMMENT.getName())){
            likeVO.setCommentVo(commentService.getSimpleById(like.getTargetId()));
        }
        return likeVO;
    }


}
