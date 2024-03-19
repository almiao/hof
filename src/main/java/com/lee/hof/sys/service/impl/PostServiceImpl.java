package com.lee.hof.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.sys.bean.UserFollowStatus;
import com.lee.hof.sys.bean.dto.*;
import com.lee.hof.sys.bean.enums.CommonStatusEnum;
import com.lee.hof.sys.bean.model.*;
import com.lee.hof.sys.bean.vo.PostSimpleVo;
import com.lee.hof.sys.bean.vo.PostVO;
import com.lee.hof.sys.mapper.*;
import com.lee.hof.sys.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    PostMapper postMapper;

    @Resource
    LikeMapper likeMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserStatisticService userStatisticService;

    @Override
    public PostSimpleVo getSimplePost(Long id) {
        Post post = postMapper.selectById(id);
        PostSimpleVo postSimpleVo = new PostSimpleVo();
        postSimpleVo.setId(post.getId());
        postSimpleVo.setAuthor(userService.getUserById(post.getAuthorId()));
        postSimpleVo.setContentText(post.getContentText());
        postSimpleVo.setImages(post.getImages());
        postSimpleVo.setTitle(post.getTitle());
        return postSimpleVo;
    }

    @Override
    public PostVO addPost(PostAddDto dto) {
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        User user = UserContext.getUser();
        post.setAuthorId(user.getId());
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        postMapper.insert(post);

        userStatisticService.addPostCnt();

        return convertPost(post);
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

    @Resource
    UserFollowMapper userFollowMapper;

    @Override
    public IPage<PostVO> listPost(PostListDto dto) {
        QueryWrapper<Post> conditions = new QueryWrapper<>();
        if(dto.getCreateBy() != null){
            conditions.eq("author_id", dto.getCreateBy());
        }

        if(StringUtils.isNotBlank(dto.getChannelName())){
            conditions.eq("channel_name", dto.getChannelName());
        }

        conditions.eq(dto.getPostType()!=null, "view_type",PostType.VOTE);


        if(StringUtils.isNotBlank(dto.getSearchText())){
            conditions.and(postQueryWrapper -> postQueryWrapper.like("title", dto.getSearchText()).or().like("content_text", dto.getSearchText()));
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setSearchText(dto.getSearchText());
            searchHistory.setCreateBy(UserContext.getUserId());
            searchHistory.setStatus(0);
            searchHistoryMapper.insert(searchHistory);
        }
        if(dto.isListMyFollow()){
            List<UserFollow> userFollow = userFollowMapper.selectList(new QueryWrapper<UserFollow>()
                    .eq("user_id", UserContext.getUserId())
                    .eq("status", UserFollowStatus.NORMAL.getCode()));
            if(userFollow.isEmpty()){
                return new Page<>();
            }
            Set<Long> userIds = userFollow.stream().map(UserFollow::getToEntityId).collect(Collectors.toSet());
            if(!userIds.isEmpty()) {
                conditions.in("author_id", userIds);
            }
        }
        conditions.orderByDesc("update_time");
        Page<Post> result = postMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions);
        return result.convert(this::convertPost);
    }

    @Resource
    private CommentService commentService;



    private PostVO convertPost(Post post){

        Long meUserId = UserContext.getUserId();
        PostVO postVO = new PostVO();

        if(post == null){
            return null;
        }

        BeanUtils.copyProperties(post, postVO);

        int cnt = likeMapper.selectCount(new QueryWrapper<Like>().eq("target_id", post.getId()).eq("status", CommonStatusEnum.INIT.getCode()));
        postVO.setLikeCnt(cnt);
        Like meLike = likeMapper.selectOne(new QueryWrapper<Like>().eq("target_id", post.getId()).eq("status", CommonStatusEnum.INIT.getCode()).eq("create_by", meUserId).last(" limit 1"));
        if(meLike!= null){
            postVO.setLike(true);
            postVO.setLikeId(meLike.getId());
        }
        int commentCnt = commentMapper.selectCount(new QueryWrapper<Comment>().eq("entity_id", post.getId()));

        Comment comment =  commentMapper.selectOne(new QueryWrapper<Comment>().eq("entity_id", post.getId())
                .last("limit 1"));
        postVO.setMostValuedComment(commentService.convert(comment));
        if(post.getViewType() == PostType.VOTE){
            PostVoteContent postVoteContent = JSONObject.parseObject(post.getVoteContent(), PostVoteContent.class);

            UserPostAction userPostAction = userPostActionService.getOne(new QueryWrapper<UserPostAction>().eq("user_id", UserContext.getUserId()).eq("post_id", post.getId()));
            if(userPostAction!=null && userPostAction.getOptionPosition()!=null && userPostAction.getOptionText()!=null){
                PostOption postOption = postVoteContent.getPostOptions().get(userPostAction.getOptionPosition());
                postOption.setMyVote(true);
                postOption.setMyVoteReason(userPostAction.getOptionComment());
            }
            postVoteContent.getPostOptions().forEach(postOption -> {
                int cntOption =  userPostActionMapper.selectCount(new QueryWrapper<UserPostAction>().eq("post_id",post.getId()).eq(
                        "option_text",postOption.getText()
                ));
                postOption.setCnt(cntOption);
            });


            postVO.setPostVoteContent(postVoteContent);
        }

        postVO.setCommentCnt(commentCnt);
        postVO.setAuthor(userService.getUserById(post.getAuthorId()));
        postVO.setTopic(topicService.getById(post.getTopicId()));

        return postVO;
    }

    @Resource
    TopicService topicService;

    @Autowired
    UserService userService;

    @Resource
    SearchHistoryService searchHistoryService;

    @Resource
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public Page<Post> searchPost(PostSearchDto dto) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>()
                .like("title",dto.getSearchText())
                .like("content_text",dto.getSearchText());
        return postMapper.selectPage(new Page<>(dto.getPageNum(),dto.getPageSize()),conditions);
    }

    @Override
    public List<String> listSearchHistory() {

        List<SearchHistory> searchHistories = searchHistoryService.list(new QueryWrapper<SearchHistory>()
        .eq("create_by", UserContext.getUserId()).eq("status", 0));

        return searchHistories.stream().map(SearchHistory::getSearchText).collect(Collectors.toList());
    }

    @Override
    public boolean delSearchHistory() {
        List<SearchHistory> searchHistories = searchHistoryService.list(new QueryWrapper<SearchHistory>()
                .eq("create_by", UserContext.getUserId()).eq("status", CommonStatusEnum.INIT));
        searchHistories.forEach(searchHistory -> {
            searchHistory.setStatus(CommonStatusEnum.HIDE.getCode());
        });
        searchHistoryService.updateBatchById(searchHistories);
        return true;
    }

    @Override
    public Boolean updatePost(PostUpdateDto postUpdateDto) {
        QueryWrapper<Post> conditions = new QueryWrapper<Post>().eq("createBy", postUpdateDto.getUser().getId()).eq("uuid", postUpdateDto.getPostId());
        Post post= postMapper.selectOne(conditions);
        if(Objects.nonNull(post)){
            BeanUtils.copyProperties(postUpdateDto,post);
            post.setUpdateTime(LocalDateTime.now());
            postMapper.updateById(post);
        }
        return true;
    }

    @Override
    public PostVO getDetail(Long postId) {
        Post post= postMapper.selectById(postId);
        if(post == null){
            throw new HofException("数据不存在");
        }
        return convertPost(post);
    }

    @Override
    public List<String> searchPostHint(PostSearchDto dto) {

        List<String> f = new ArrayList<>();

        QueryWrapper<Post> conditions = new QueryWrapper<Post>()
                .like("title",dto.getSearchText()).or()
                .like("content_text",dto.getSearchText());
        List<Post> result = postMapper.selectList(conditions);

        result.stream().forEach(post -> {
            if(post.getTitle()!= null && post.getTitle().contains(dto.getSearchText())){
                int startIndex = post.getTitle().indexOf(dto.getSearchText());
                int endIndex = Math.min(startIndex+3, post.getTitle().length());
                f.add(post.getTitle().substring(startIndex, endIndex));
            }

            if(post.getContentText()!= null && post.getContentText().contains(dto.getSearchText())) {
                int startIndex = post.getContentText().indexOf(dto.getSearchText());
                int endIndex = Math.min(startIndex+3, post.getContentText().length());
              f.add(post.getContentText().substring(startIndex, endIndex));
            }
        });
        return f;
    }


    @Resource
    UserPostActionService userPostActionService;

    @Resource
    UserPostActionMapper userPostActionMapper;

    @Override
    public UserPostAction addOrUpdatePostAction(PostVoteDto dto) {
        Post post = postMapper.selectById(dto.getPostId());
        if(post == null){
            throw new HofException("post不存在");
        }
        UserPostAction userPostAction = userPostActionService.getOne(new QueryWrapper<UserPostAction>().eq("user_id", UserContext.getUserId()).eq("post_id", dto.getPostId()));
        if(userPostAction == null){
            userPostAction = new UserPostAction();
            userPostAction.setOptionText(dto.getOption());
            userPostAction.setOptionComment(dto.getOptionComment());
            userPostAction.setUserId(UserContext.getUserId());
            userPostAction.setPostId(dto.getPostId());
            if(dto.isAddLike()) {
                userPostAction.setIsLike(1);
            }
            if(dto.isDelLike()){
                userPostAction.setIsLike(0);
            }
            if(dto.isAddUnlike()){
                userPostAction.setIsUnlike(1);
            }
            if(dto.isDelUnlike()){
                userPostAction.setIsUnlike(0);
            }
            userPostActionMapper.insert(userPostAction);
        }else{
            userPostAction.setOptionText(dto.getOption());
            userPostAction.setOptionComment(dto.getOptionComment());
            userPostAction.setUserId(UserContext.getUserId());
            userPostAction.setPostId(dto.getPostId());
            if(dto.isAddLike()) {
                userPostAction.setIsLike(1);
            }
            if(dto.isDelLike()){
                userPostAction.setIsLike(0);
            }
            if(dto.isAddUnlike()){
                userPostAction.setIsUnlike(1);
            }
            if(dto.isDelUnlike()){
                userPostAction.setIsUnlike(0);
            }
            userPostActionMapper.updateById(userPostAction);
        }
        return userPostAction;
    }

    @Override
    public PostVO getOneTodo(Long startId) {
        Post post =  postMapper.selectOne(new QueryWrapper<Post>().eq("view_type", PostType.VOTE)
                        .gt("id", startId)
                .last("limit 1"));
        return convertPost(post);
    }

    @Override
    public List<UserPostAction> listAction() {

        List<UserPostAction> userPostAction = userPostActionService.list(new QueryWrapper<UserPostAction>().eq("user_id", UserContext.getUserId()));

        return userPostAction;
    }


}
