package com.lee.hof.sys.bean.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CommentAddDto implements Serializable {

    private Long entityId;
    private Long parentCommentId;
    private Long replyToCommentId;
    private Long userId;
    private String commentText;
    private String extension;
    private Long replyToUserId;


}
