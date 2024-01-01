package com.lee.hof.sys.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */
@Data
public class CommentMineVO implements Serializable {

    private static final long serialVersionUID = 18766556543364580L;

    private PostSimpleVo post;

    private Comment replyToComment;

    private User replyToUser;

    private Long id;

    private String entityId;

    private Long parentCommentId;

    private Long replyToUserId;

    private Long replyToCommentId;

    private Long userId;

    private User user;

    private String commentTxt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    public CommentMineVO(Comment comment){
        this.id = comment.getId();
        this.entityId = comment.getEntityId();
        this.parentCommentId = comment.getParentCommentId();
        this.commentTxt = comment.getCommentTxt();
        this.createTime = comment.getCreateTime();
        this.replyToCommentId = comment.getReplyToCommentId();
        this.updateTime = comment.getUpdateTime();
        this.userId = comment.getUserId();
    }

}


