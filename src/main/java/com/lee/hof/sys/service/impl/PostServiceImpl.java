package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.PostVO;
import com.lee.hof.sys.mapper.CommentMapper;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.PostService;
import com.lee.hof.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Resource
    LikeMapper likeMapper;

    @Resource
    CommentMapper commentMapper;

    @Override
    public PostVO addPost(PostAddDto dto) {
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        post.setId(UUID.randomUUID().toString());
        User user = UserContext.getUser();
        post.setAuthorId(user.getId());
        postMapper.insert(post);

        return convertPost(post, user.getId());
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

        Long meUserId = UserContext.getUserId();


        IPage<PostVO> list = result.convert(post -> {
            return convertPost(post,meUserId);
        });
        return list;
    }

    private PostVO convertPost(Post post,long meUserId){

        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);

        int cnt = likeMapper.selectCount(new QueryWrapper<Like>().eq("target_id", post.getId()).eq("level",0).eq("is_del", 0));
        postVO.setLikeCnt(cnt);
        Like meLiks = likeMapper.selectOne(new QueryWrapper<Like>().eq("target_id", post.getId()).eq("level",0).eq("is_del", 0).eq("create_by", meUserId).last(" limit 1"));
        if(meLiks!= null){
            postVO.setLike(true);
            postVO.setLikeId(meLiks.getId());
        }
        int commentCnt = commentMapper.selectCount(new QueryWrapper<Comment>().eq("post_id", post.getId()));
        postVO.setCommentCnt(commentCnt);

        postVO.setAuthor(userService.getUserById(post.getAuthorId()));

        return postVO;
    }

    @Autowired
    UserService userService;

    @Override
    public Page<Post> searchPost(PostListDto dto) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>()
                .eq("createBy", dto.getUser().getId())
                .like(StringUtils.isNotEmpty(dto.getPostName()),"name",dto.getPostName())
                .like(StringUtils.isNotEmpty(dto.getContent()),"contentHtml",dto.getContent());

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
        postDetailVo.setAuthor(userService.getUserById(post.getAuthorId()));
        return postDetailVo;
    }


}
