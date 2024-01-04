package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.enums.CommonStatusEnum;
import com.lee.hof.sys.bean.enums.EntityTypeEnum;
import com.lee.hof.sys.bean.model.Collect;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.vo.CollectVO;
import com.lee.hof.sys.mapper.CollectMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    CollectMapper collectMapper;
    @Resource
    PostMapper postMapper;

    @Resource
    UserStatisticService userStatisticService;

    @Override
    public Long add(Collect like) {
        Collect db = collectMapper.selectOne(new QueryWrapper<Collect>().eq("create_by",
                        UserContext.getUserId()).eq("target_id", like.getTargetId())
                .eq("status",0).orderByAsc().last("limit 1"));
        if(db != null){
            return db.getId();
        }
        like.setCreateBy(UserContext.getUserId());
        like.setCreateTime(new Timestamp(System.currentTimeMillis()));
        like.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        like.setStatus(CommonStatusEnum.INIT.getCode());
        collectMapper.insert(like);
        userStatisticService.addCollectCnt();
        return like.getId();
    }

    @Override
    public Long undoLike(UndoLike id) {
        Collect like = collectMapper.selectOne(new QueryWrapper<Collect>().eq("create_by", UserContext.getUserId()).eq("target_id", id.getTargetId())
        .eq("status",0).orderByAsc().last("limit 1"));
        if(like == null){
            return null;
        }
        like.setStatus(CommonStatusEnum.DELETE.getCode());
        collectMapper.updateById(like);
        return like.getId();
    }

    @Override
    public List<CollectVO> listLikeMe() {
        List<Post> myPosts = postMapper.selectList(new QueryWrapper<Post>().eq("author_id", UserContext.getUserId()));
        Set<Long> postId = myPosts.stream().map(Post::getId).collect(Collectors.toSet());

        List<Comment> myComments = commentService.list(new QueryWrapper<Comment>().eq("create_by", UserContext.getUserId()));
        Set<Long> commentId = myComments.stream().map(Comment::getId).collect(Collectors.toSet());

        List<Collect> likes = collectMapper.selectList(new QueryWrapper<Collect>().eq("status",0)
                .and(likeQueryWrapper -> likeQueryWrapper.eq("target_entity_type","post").in("target_id",postId)
                .or().eq("target_entity_type","comment").in("target_id",commentId)).orderByDesc("id"));

        return likes.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<CollectVO> listMyLike() {
        List<Collect> collects = collectMapper.selectList(new QueryWrapper<Collect>().eq("create_by", UserContext.getUserId())
                .eq("status", 0).orderByDesc());

        return collects.stream().map(this::convert).collect(Collectors.toList());
    }

    @Resource
    PostService postService;

    @Resource
    CommentService commentService;


    @Resource
    UserService userService;

    private CollectVO convert(Collect collect){
        if(collect == null){
            return null;
        }
        CollectVO likeVO = new CollectVO();
        BeanUtils.copyProperties(collect, likeVO);
        likeVO.setUser(userService.getUserById(collect.getCreateBy()));

        if(Objects.equals(collect.getTargetEntityType(), EntityTypeEnum.POST.getName())){
            likeVO.setPost(postService.getSimplePost(collect.getTargetId()));
        }else if(Objects.equals(collect.getTargetEntityType(), EntityTypeEnum.COMMENT.getName())){
            likeVO.setCommentVo(commentService.getSimpleById(collect.getTargetId()));
        }
        return likeVO;
    }


}
