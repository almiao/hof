package com.lee.hof.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.PostVO;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.PostService;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    PostMapper postMapper;

    @Resource
    LikeMapper likeMapper;

    @Override
    public String addPost(PostAddDto dto) {
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        post.setId(UUID.randomUUID().toString());
        postMapper.insert(post);
        return post.getId();
    }

    @Override
    public Boolean delPost(User user, String postId) {

        QueryWrapper<Post> conditions = new QueryWrapper<Post>().eq("createBy", user.getId()).eq("uuid", postId);

        Post post= postMapper.selectOne(conditions);

        if(Objects.nonNull(post)){
           post.setStatus(99);
           post.setUpdateBy(user.getId());
           postMapper.updateById(post);
        }
        return true;
    }

    @Override
    public IPage<PostVO> listPost(PostListDto dto) {
        QueryWrapper<Post> conditions = new QueryWrapper<>();
        if(dto.getUser()!= null && dto.getUserId()!=null){
            conditions.eq("create_by", dto.getUserId());
        }
        conditions.orderByDesc("update_time");
        Page<Post> result = postMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions);

        List<String> postId = result.getRecords().stream().map(Post::getId).collect(Collectors.toList());
        Map<String,Like> likeMap = new HashMap<>();
        Map<String,Like> notLikeMap = new HashMap<>();
        if(CollectionUtil.isNotEmpty(postId)){

            List<Like> likes = likeMapper.selectList(new QueryWrapper<Like>().in("target_id",postId).eq("level",0)
                    .eq("create_by",dto.getUserId()));

            likeMap = likes.stream().collect(Collectors.toMap(Like::getTargetId, Function.identity()));

            List<Like> notLikes = likeMapper.selectList(new QueryWrapper<Like>().in("target_id",postId).eq("level",1)
                    .eq("create_by",dto.getUserId()));

            notLikeMap = notLikes.stream().collect(Collectors.toMap(Like::getTargetId, Function.identity()));

        }


        Map<String, Like> finalLikeMap = likeMap;
        Map<String, Like> finalNotLikeMap = notLikeMap;
        IPage<PostVO> list = result.convert(post -> {
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(post, postVO);

            if(finalLikeMap.containsKey(post.getId())){
                postVO.setLike(true);
                postVO.setLikeId(finalLikeMap.get(post.getId()).getId());
            }

            if(finalNotLikeMap.containsKey(post.getId())){
                postVO.setNotLike(true);
                postVO.setNotLikeId(finalNotLikeMap.get(post.getId()).getId());
            }
            return postVO;
        });

        return list;
    }

    @Override
    public Page<Post> searchPost(PostListDto dto) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>()
                .eq("createBy", dto.getUser().getId())
                .like(StringUtil.isNotEmpty(dto.getPostName()),"name",dto.getPostName())
                .like(StringUtil.isNotEmpty(dto.getContent()),"contentHtml",dto.getContent());

        return postMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions);
    }

    @Override
    public Boolean updatePost(PostUpdateDto postUpdateDto) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>().eq("createBy", postUpdateDto.getUser().getId()).eq("uuid", postUpdateDto.getPostId());
        Post post= postMapper.selectOne(conditions);
        if(Objects.nonNull(post)){
            BeanUtils.copyProperties(postUpdateDto,post);
            postMapper.updateById(post);
        }
        return true;
    }

    @Override
    public PostVO getDetail(String postId) {
        Post post= postMapper.selectById(postId);
        PostVO postDetailVo = new PostVO();
        if(Objects.nonNull(post)){
            BeanUtils.copyProperties(post,postDetailVo);
        }
        return postDetailVo;
    }


}
