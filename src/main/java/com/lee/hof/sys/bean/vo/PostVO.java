package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.PostOption;
import com.lee.hof.sys.bean.model.Topic;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.util.List;


@Data
public class PostVO extends Post {

    private boolean like;

    private int commentCnt;

    private boolean notLike;

    private String likeId;

    private String notLikeId;

    private int likeCnt;

    private User author;

    private Topic topic;

    private CommentVo mostValuedComment;

    private List<PostOption> postOptions;

}
