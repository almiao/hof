package com.lee.hof.sys.bean.dto;


import com.lee.hof.sys.bean.model.User;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CommentDto implements Serializable {

    @NonNull
    private String postId;

    private String toCommentId;

    @NonNull
    private Long userId;

    @NotBlank(message = "评论信息不能为空")
    private String commentInfo;

    private User toUser;

}
