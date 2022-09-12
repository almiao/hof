package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.EntityType;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

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


    @Override
    public String add(Like like) {
        like.setCreateTime(new Timestamp(System.currentTimeMillis()));
        like.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        likMapper.insert(like);

        if(like.getTargetEntityType().equals(EntityType.POST.getName())){
            Post post = postMapper.selectById(like.getTargetId());
            if(like.getLevel() == 0){
                post.setLikeCnt(post.getLikeCnt() +1);
            }else if(like.getLevel() ==1 ){
                post.setNotLikeCnt(post.getNotLikeCnt() +1);
            }
            postMapper.updateById(post);
        }
        return like.getId();
    }

    @Override
    public String cnt() {
        return null;
    }

    @Override
    public String undoLike(UndoLike id) {

        Like like = likMapper.selectOne(new QueryWrapper<Like>().eq("create_by", id.getCreateBy()).eq("target_id", id.getTargetId())
        .eq("level", id.getLevel()).orderByAsc());
        if(like == null){
            return null;
        }

        likMapper.deleteById(like.getId());

        if(like.getTargetEntityType().equals(EntityType.POST.getName())){
            Post post = postMapper.selectById(like.getTargetId());
            if(like.getLevel() == 0){
                post.setLikeCnt(post.getLikeCnt() - 1);
            }else if(like.getLevel() ==1 ){
                post.setNotLikeCnt(post.getNotLikeCnt() - 1);
            }
            postMapper.updateById(post);
        }

        return like.getId();
    }
}
