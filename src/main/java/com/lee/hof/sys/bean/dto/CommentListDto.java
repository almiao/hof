package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CommentListDto extends BaseInput {

    public String postId;

    private String toCommentId;

    private int pageNum = 0;

    private int pageSize = 10;

}
