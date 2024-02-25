package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.*;
import lombok.Data;

import java.util.List;


@Data
public class PostVO extends Post {

    private boolean like;

    private int commentCnt;

    private boolean notLike;

    private Long likeId;

    private String notLikeId;

    private int likeCnt;

    private User author;

    private Topic topic;

    private CommentVo mostValuedComment;

    private PostVoteContent postVoteContent;

}
