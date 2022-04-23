package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.PostDetailVo;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.PostService;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

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

    @Override
    public String addPost(PostAddDto dto) {
        Post post = new Post();
        post.setUuid(UUID.randomUUID().toString());
        post.setContentHtml(dto.getPostContentHtml());
        post.setContentPicList(dto.getContentPics());
        post.setHeadText(dto.getPostHead());
        post.setName(dto.getPostName());
        post.setStatus(1);
        postMapper.insert(post);
        return post.getUuid();
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
    public Page<Post> listPost(PostListDto dto) {
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
    public PostDetailVo getDetail(User user, String postId) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>().eq("createBy", user.getId()).eq("uuid", postId);

        Post post= postMapper.selectOne(conditions);
        PostDetailVo postDetailVo = new PostDetailVo();
        if(Objects.nonNull(post)){
            BeanUtils.copyProperties(post,postDetailVo);
        }
        return postDetailVo;
    }


}
