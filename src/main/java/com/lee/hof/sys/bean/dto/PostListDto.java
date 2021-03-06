package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.util.Date;


@Data
public class PostListDto extends BaseInput {

    public String postId;

    public String postName;

    public String content;

    public Date startDate;

    private Date endDate;


    private int pageNum = 0;

    private int pageSize = 99;

    private String sortBy;
}
