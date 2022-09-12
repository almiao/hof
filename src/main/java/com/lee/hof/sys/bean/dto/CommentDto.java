package com.lee.hof.sys.bean.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {

    private String postId;

    private String toCommentId;

    private Long userId;

    private String commentInfo;

    private String createTime;

    private String updateTime;

}
