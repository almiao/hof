package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.model.Like;
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
        like.setCreateBy(UserContext.getUserId());
        like.setCreateTime(new Timestamp(System.currentTimeMillis()));
        like.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        likMapper.insert(like);
        return like.getId();
    }

    @Override
    public String cnt() {
        return null;
    }

    @Override
    public String undoLike(UndoLike id) {

        Like like = likMapper.selectOne(new QueryWrapper<Like>().eq("create_by", id.getCreateBy()).eq("target_id", id.getTargetId())
        .eq("level", id.getLevel()).eq("is_del",0).orderByAsc());
        if(like == null){
            return null;
        }
        like.setIsDel(1);

        likMapper.updateById(like);

        return like.getId();
    }
}
