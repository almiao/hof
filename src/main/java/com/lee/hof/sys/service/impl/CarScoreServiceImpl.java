package com.lee.hof.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.model.CarScore;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.PostType;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.CarScoreMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.CarScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class CarScoreServiceImpl extends ServiceImpl<CarScoreMapper, CarScore> implements CarScoreService {


    @Resource
    PostMapper postMapper;


    @Override
    public CarScore add(CarScore like) {
        this.baseMapper.insert(like);

        Post post = new Post();
        User user = UserContext.getUser();
        post.setAuthorId(user.getId());
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        post.setViewType(PostType.CarScore);
        post.setContentText(JSONObject.toJSONString(like));
        post.setCreateBy(user.getId());
        post.setTitle(like.getCarSeries());
        postMapper.insert(post);
        return like;
    }
}
