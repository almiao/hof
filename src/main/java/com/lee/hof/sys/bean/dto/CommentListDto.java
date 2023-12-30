package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CommentListDto extends BaseInput {

    public Long entityId;

    private Long parentCommentId;

    private int orderBy;

    private Long authorId;

    private int pageNum = 0;

    private int pageSize = 100;

}
