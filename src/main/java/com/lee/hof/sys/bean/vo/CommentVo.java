package com.lee.hof.sys.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentVo implements Serializable {

    private Long id;

    private Long entityId;

    private Long parentCommentId;

    private Long toCommentId;

    private CommentVo replyTo;

    private Long userId;

    private String commentTxt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp updateTime;

    private User user;

    private User toUser;

    private String updateTimeStr;

    private int replyCnt;

    private List<CommentVo> replyList = new ArrayList<>();

    private List<Like> likeList = new ArrayList<>();


    public CommentVo(){
    }

    public CommentVo(Comment comment){
        this.id = comment.getId();
        this.entityId = comment.getEntityId();
        this.parentCommentId = comment.getParentCommentId();
        this.commentTxt = comment.getCommentTxt();
        this.createTime = comment.getCreateTime();
        this.toCommentId = comment.getReplyToCommentId();
        this.updateTime = comment.getUpdateTime();
        this.updateTimeStr = Utils.formatTime(comment.getUpdateTime().getTime());
        this.userId = comment.getCreateBy();
    }


}
