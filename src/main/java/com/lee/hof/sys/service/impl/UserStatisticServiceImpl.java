package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.model.*;
import com.lee.hof.sys.bean.vo.UserStatisticVO;
import com.lee.hof.sys.mapper.CommentMapper;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.mapper.UserStatisticMapper;
import com.lee.hof.sys.service.UserStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
public class UserStatisticServiceImpl extends ServiceImpl<UserStatisticMapper, UserStatistic> implements UserStatisticService {


    @Override
    public UserStatistic add(UserStatistic input) {

        UserStatistic userStatistic = getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("user_id", UserContext.getUserId()));

        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setUserId(UserContext.getUserId());
        }

        if(input.getLikeReadId() > 0){
            userStatistic.setLikeReadId(input.getLikeReadId());
        }
        if(input.getCommentReadId() > 0){
            userStatistic.setCollectReadId(input.getCollectReadId());
        }

        if(input.getCommentReadId() > 0){
            userStatistic.setCommentReadId(input.getCommentReadId());
        }

        if(userStatistic.getId() == null){
            userStatistic.setUserId(UserContext.getUserId());
            userStatistic.setCreateBy(UserContext.getUserId());
            getBaseMapper().insert(userStatistic);
        }else{
            getBaseMapper().updateById(userStatistic);
        }
        return userStatistic;
    }

    @Resource
    LikeMapper likeMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    PostMapper postMapper;


    @Override
    public UserStatisticVO getMine(Long userId) {
        UserStatistic userStatistic = getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("create_by", userId));
        UserStatisticVO result = new UserStatisticVO();
        if(userStatistic == null){
            userStatistic = new UserStatistic();
        }
        List<Post> myPosts = postMapper.selectList(new QueryWrapper<Post>().select("id").eq("author_id", userId));
        Set<Long> myPostIds = myPosts.stream().map(Post::getId).collect(Collectors.toSet());
        List<Comment> myComments = commentMapper.selectList(new QueryWrapper<Comment>().select("id").eq("create_by",userId));
        Set<Long> myCommentIds = myComments.stream().map(Comment::getId).collect(Collectors.toSet());

        if(!myCommentIds.isEmpty()){
            List<Like> likes = likeMapper.selectList(new QueryWrapper<Like>()
                    .eq("status", 0 )
                    .in("target_id",myCommentIds)
                    .eq("target_entity_type","comment")
                    .gt("id", userStatistic.getLikeReadId())
                    .orderByDesc("id"));
            result.setNeedReadLikeCnt(likes.size());
            if(!likes.isEmpty()){
                result.setLastLikeId(likes.get(0).getId());
            }
        }

        if(!myPostIds.isEmpty()){
            List<Like> likes = likeMapper.selectList(new QueryWrapper<Like>()
                    .eq("status", 0 )
                    .eq("target_entity_type","post")
                    .in("target_id",myPostIds)
                    .gt("id", userStatistic.getLikeReadId())
                    .orderByDesc("id"));
            result.setNeedReadLikeCnt(likes.size() + result.getNeedReadLikeCnt());
            if(!likes.isEmpty()){
                result.setLastLikeId(Math.max(result.getLastLikeId(),likes.get(0).getId()));
            }
        }

        QueryWrapper<Comment> conditions = new QueryWrapper<>();
        UserStatistic finalUserStatistic = userStatistic;
        conditions.and(commentQueryWrapper ->commentQueryWrapper.gt("id", finalUserStatistic.getCommentReadId()));

        if(!myPostIds.isEmpty()){
            conditions.and(commentQueryWrapper -> commentQueryWrapper.eq("reply_to_user_id", userId).or().in("entity_id", myPostIds));
        }else{
            conditions.and(commentQueryWrapper -> commentQueryWrapper.eq("reply_to_user_id", userId));
        }
        conditions.orderByDesc("id");

        List<Comment> newReadComment = commentMapper.selectList(conditions);

        result.setUserId(userStatistic.getUserId());
        result.setCommentReadId(userStatistic.getCommentReadId());
        result.setNeedReadCollectCnt(0);

        result.setNeedReadCommentCnt(newReadComment.size());

        if(!newReadComment.isEmpty()){
            result.setLastCommentId(newReadComment.get(0).getId());
        }
        return result;
    }
    private UserStatistic getMyStatic(){
        UserStatistic userStatistic = getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("create_by", UserContext.getUserId()));
        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setCreateBy(UserContext.getUserId());
            userStatistic.setUserId(UserContext.getUserId());
            getBaseMapper().insert(userStatistic);
        }

        return userStatistic;
    }

    @Override
    public void addPostCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setPostCnt(userStatistic.getPostCnt() + 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void addCommentCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setCommentCnt(userStatistic.getCommentCnt() + 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void addLikeCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setLikeCnt(userStatistic.getLikeCnt() + 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void addCollectCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setCollectCnt(userStatistic.getCollectCnt() + 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void addFollowCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setFollowCnt(userStatistic.getFollowCnt() + 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void delFollowCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setFollowCnt(userStatistic.getFollowedCnt() - 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void delPostCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setPostCnt(userStatistic.getPostCnt() - 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void delCommentCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setCommentCnt(userStatistic.getCommentCnt() - 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void delLikeCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setLikeCnt(userStatistic.getLikeCnt() - 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void delCollectCnt() {
        UserStatistic userStatistic = getMyStatic();
        userStatistic.setCollectCnt(userStatistic.getCollectCnt() - 1);
        getBaseMapper().updateById(userStatistic);
    }

    @Override
    public void addLikedCnt(EntityType entityType, Long entityId) {

        UserStatistic userStatistic;
        Long authorId = null;
        if(entityType == EntityType.POST){
            Post post = postMapper.selectById(entityId);
            authorId = post.getAuthorId();
        }else{
            Comment post = commentMapper.selectById(entityId);
            authorId = post.getCreateBy();
        }
        userStatistic = getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("create_by", authorId));
        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setLikedCnt(1);
            userStatistic.setCreateBy(authorId);
            getBaseMapper().insert(userStatistic);
        }else{
            userStatistic.setLikedCnt(userStatistic.getLikedCnt() +1);
            getBaseMapper().updateById(userStatistic);
        }
    }

    @Override
    public void addFollowedCnt(Long entityId) {
        UserStatistic userStatistic = getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("create_by", entityId));
        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setFollowedCnt(1);
            userStatistic.setCreateBy(entityId);
            getBaseMapper().insert(userStatistic);
        }else{
            userStatistic.setFollowedCnt(userStatistic.getFollowedCnt() +1);
            getBaseMapper().updateById(userStatistic);
        }

    }


}
