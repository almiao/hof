package com.lee.hof.sys.bean.dto;


import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {

    @NonNull
    private Long entityId;

    private Long parentCommentId;

    private Long replyToCommentId;

    private Long replyToUserId;


    @NotBlank(message = "评论信息不能为空")
    private String commentText;

    private String extension;


}
