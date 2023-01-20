package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.PostAddDto;
import com.lee.hof.sys.bean.dto.PostListDto;
import com.lee.hof.sys.bean.dto.PostUpdateDto;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.PostVO;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface PostService extends IService<Post> {

    PostVO addPost(PostAddDto dto);

    Boolean delPost(User user, String postId);

    IPage<PostVO> listPost(PostListDto postListDto);

    Page<Post> searchPost(PostListDto postListDto);

    Boolean updatePost(PostUpdateDto postUpdateDto);

    PostVO getDetail(String postId);
}
